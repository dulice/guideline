package com.example.guideline.dto;

import com.example.guideline.model.Guideline;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
public class StepRequestDto {
    private String title;
    private String titleMm;
    private String stepKey;
    private String image;
}
