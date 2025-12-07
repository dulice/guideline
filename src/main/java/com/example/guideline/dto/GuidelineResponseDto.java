package com.example.guideline.dto;

import com.example.guideline.model.GuidelineStep;
import com.example.guideline.model.GuidelineTypeEnum;
import com.example.guideline.model.Step;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class GuidelineResponseDto {
    private String id;
    private String title;
    private String titleMm;
    private String subtitle;
    private String subtitleMm;
    private String deepLink;
    private GuidelineTypeEnum guidelineType;
    private String image;
    private List<GuidelineStepResponseDto> steps;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
