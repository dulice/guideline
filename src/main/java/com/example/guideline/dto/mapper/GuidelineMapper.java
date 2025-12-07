package com.example.guideline.dto.mapper;

import com.example.guideline.dto.GuidelineResponseDto;
import com.example.guideline.dto.GuidelineStepResponseDto;
import com.example.guideline.model.Guideline;
import com.example.guideline.model.GuidelineStep;

import java.util.ArrayList;
import java.util.List;

public class GuidelineMapper {
    public static GuidelineResponseDto toDto(Guideline guideline) {
        GuidelineResponseDto guidelineResponseDto = new GuidelineResponseDto();
        guidelineResponseDto.setId(guideline.getId());
        guidelineResponseDto.setTitle(guideline.getTitle());
        guidelineResponseDto.setTitleMm(guideline.getTitleMm());
        guidelineResponseDto.setSubtitle(guideline.getSubtitle());
        guidelineResponseDto.setSubtitleMm(guideline.getSubtitleMm());
        guidelineResponseDto.setImage(guideline.getImage());
        guidelineResponseDto.setDeepLink(guideline.getDeepLink());
        guidelineResponseDto.setGuidelineType(guideline.getGuidelineType());
        guidelineResponseDto.setSteps(getGuidelineStepResponseDtos(guideline));
        guidelineResponseDto.setCreatedAt(guideline.getCreatedAt());
        guidelineResponseDto.setUpdatedAt(guideline.getUpdatedAt());
        return guidelineResponseDto;

    }

    private static List<GuidelineStepResponseDto> getGuidelineStepResponseDtos(Guideline guideline) {
        List<GuidelineStepResponseDto> guidelineStepResponseDtoList = new ArrayList<>();
        for (GuidelineStep step : guideline.getSteps()) {
            GuidelineStepResponseDto guidelineStepResponseDto = new GuidelineStepResponseDto();
            guidelineStepResponseDto.setNo(step.getNo());
            guidelineStepResponseDto.setTitle(step.getStep().getTitle());
            guidelineStepResponseDto.setTitleMm(step.getStep().getTitleMm());
            guidelineStepResponseDto.setImage(step.getStep().getImage());
            guidelineStepResponseDto.setStepKey(step.getStep().getStepKey());

            guidelineStepResponseDtoList.add(guidelineStepResponseDto);
        }
        return guidelineStepResponseDtoList;
    }
}
