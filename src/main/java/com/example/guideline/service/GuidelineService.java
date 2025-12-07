package com.example.guideline.service;

import com.example.guideline.dto.GuidelineRequestDto;
import com.example.guideline.model.GuidelineTypeEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface GuidelineService {
    public ResponseEntity<?> getGuideline(GuidelineTypeEnum guidelineType);
    public ResponseEntity<?> getGuidelines();
    public ResponseEntity<?> createGuideline(MultipartFile image, GuidelineRequestDto guidelineRequestDto);
    public ResponseEntity<?> updateGuideline(String id, MultipartFile image, GuidelineRequestDto guidelineRequestDto);
    public ResponseEntity<?> deleteGuideline(String id);
}
