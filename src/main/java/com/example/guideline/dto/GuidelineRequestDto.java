package com.example.guideline.dto;

import com.example.guideline.model.GuidelineStep;
import com.example.guideline.model.GuidelineTypeEnum;
import com.example.guideline.model.Step;
import lombok.Data;

import java.util.List;

@Data
public class GuidelineRequestDto {

    private String title;
    private String titleMm;
    private String subtitle;
    private String subtitleMm;
    private String deepLink;
    private String image;
    private GuidelineTypeEnum guidelineType;
    private List<GuidelineStepRequestDto> steps;

}
