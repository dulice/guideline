package com.example.guideline.service.imp;

import com.example.guideline.dto.StepRequestDto;
import com.example.guideline.model.Step;
import com.example.guideline.repository.GuidelineStepRepository;
import com.example.guideline.repository.StepRepository;
import com.example.guideline.service.StepService;
import com.example.guideline.utils.S3Image;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StepServiceImp implements StepService {

    private final StepRepository stepRepository;
    private final GuidelineStepRepository guidelineStepRepository;

    @Override
    public ResponseEntity<?> getAllSteps() {
        List<Step> steps = stepRepository.findAll();
        return ResponseEntity.ok().body(steps);
    }

    @Override
    public ResponseEntity<?> getStepById(String id) {
        Step step = stepRepository.findById(id).orElseThrow(() -> new RuntimeException("Step Not Found"));
        return ResponseEntity.ok(step);
    }

    @Override
    public ResponseEntity<?> createStep(MultipartFile image, StepRequestDto stepRequestDto) {
        String imageUrl = S3Image.upload(image);

        Step step = new Step();
        step.setStepKey(stepRequestDto.getStepKey());
        step.setTitle(stepRequestDto.getTitle());
        step.setTitleMm(stepRequestDto.getTitleMm());
        step.setImage(imageUrl);
        return ResponseEntity.ok(stepRepository.save(step));
    }

    @Override
    public ResponseEntity<?> updateStep(String id, MultipartFile image, StepRequestDto stepRequestDto) {
        Step step = stepRepository.findById(id).orElseThrow(() -> new RuntimeException("Step Not Found"));
        String imageUrl = stepRequestDto.getImage();
        if (image != null) {
            S3Image.delete(step.getImage());
            imageUrl = S3Image.upload(image);
        }
        step.setStepKey(stepRequestDto.getStepKey());
        step.setTitle(stepRequestDto.getTitle());
        step.setTitleMm(stepRequestDto.getTitleMm());
        step.setImage(imageUrl);
        return ResponseEntity.ok(stepRepository.save(step));
    }


    @Override
    @Transactional
    public ResponseEntity<?> deleteStep(String id) {
        Step step = stepRepository.findById(id).orElseThrow(() -> new RuntimeException("Step Not Found"));

        guidelineStepRepository.deleteByStepId(step.getId());
        S3Image.delete(step.getImage());
        stepRepository.deleteById(id);
        return ResponseEntity.ok("Step Deleted");
    }
}
