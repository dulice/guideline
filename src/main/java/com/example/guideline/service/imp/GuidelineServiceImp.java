package com.example.guideline.service.imp;

import com.example.guideline.dto.GuidelineRequestDto;
import com.example.guideline.dto.GuidelineResponseDto;
import com.example.guideline.dto.GuidelineStepRequestDto;
import com.example.guideline.dto.mapper.GuidelineMapper;
import com.example.guideline.model.*;
import com.example.guideline.repository.GuidelineRepository;
import com.example.guideline.repository.GuidelineStepRepository;
import com.example.guideline.repository.StepRepository;
import com.example.guideline.service.GuidelineService;
import com.example.guideline.utils.S3Image;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GuidelineServiceImp implements GuidelineService {

    private final GuidelineRepository guidelineRepository;
    private final StepRepository stepRepository;
    private final GuidelineStepRepository guidelineStepRepository;

    @Override
    @Transactional
    public ResponseEntity<?> getGuideline(GuidelineTypeEnum guidelineType) {
        List<GuidelineResponseDto> guidelines = guidelineRepository.findByGuidelineType(guidelineType).stream().map(GuidelineMapper::toDto).toList();
        return ResponseEntity.ok(guidelines);
    }

    @Override
    @Transactional
    public ResponseEntity<?> getGuidelines() {
        List<GuidelineResponseDto> guidelines = guidelineRepository.findAll().stream().map(GuidelineMapper::toDto).toList();
        return ResponseEntity.ok(guidelines);
    }

    @Override
    @Transactional
    public ResponseEntity<?> createGuideline(MultipartFile image, GuidelineRequestDto guidelineRequestDto) {
        String imageUrl = S3Image.upload(image);
        Guideline newGuideline = savedGuideline(guidelineRequestDto, imageUrl);
        linkGuidelineAndStep(newGuideline, guidelineRequestDto.getSteps());
        GuidelineResponseDto guidelineResponseDto = GuidelineMapper.toDto(newGuideline);
        return ResponseEntity.ok(guidelineResponseDto);
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateGuideline(String id, MultipartFile image, GuidelineRequestDto guidelineRequestDto) {
        Guideline guideline = guidelineRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
        String imageUrl = guidelineRequestDto.getImage();
        if(image != null) {
            S3Image.delete(guideline.getImage());
            imageUrl = S3Image.upload(image);
            guideline.setImage(imageUrl);
        }
        Guideline newGuideline = savedGuideline(guidelineRequestDto, imageUrl);
        guideline.getSteps().clear();
        linkGuidelineAndStep(newGuideline, guidelineRequestDto.getSteps());
        GuidelineResponseDto guidelineResponseDto = GuidelineMapper.toDto(newGuideline);
        return ResponseEntity.ok(guidelineResponseDto);
    }

    @Override
    public ResponseEntity<?> deleteGuideline(String id) {
        Guideline guideline =guidelineRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
        S3Image.delete(guideline.getImage());
        guidelineRepository.deleteById(id);
        return ResponseEntity.ok("Image deleted");
    }

    private void linkGuidelineAndStep(Guideline guideline, List<GuidelineStepRequestDto> steps) {
        for (GuidelineStepRequestDto stepDto : steps) {
            Step step = stepRepository.findById(stepDto.getStepId()).orElseThrow(() -> new RuntimeException("Id not found"));
            GuidelineStep guidelineStep = new GuidelineStep();
            guidelineStep.setGuideline(guideline);
            guidelineStep.setStep(step);
            guidelineStep.setNo(stepDto.getNo());
            guidelineStep.setId(new GuidelineStepId(guideline.getId(), step.getId()));
            guideline.getSteps().add(guidelineStep);
        }
    }

    private Guideline savedGuideline(GuidelineRequestDto guidelineRequestDto, String imageUrl) {
        Guideline guideline = new Guideline();
        guideline.setImage(imageUrl);
        guideline.setTitle(guidelineRequestDto.getTitle());
        guideline.setTitleMm(guidelineRequestDto.getTitleMm());
        guideline.setSubtitle(guidelineRequestDto.getSubtitle());
        guideline.setSubtitleMm(guidelineRequestDto.getSubtitleMm());
        guideline.setDeepLink(guidelineRequestDto.getDeepLink());
        guideline.setGuidelineType(guidelineRequestDto.getGuidelineType());
        return guidelineRepository.save(guideline);
    }
}
