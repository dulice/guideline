package com.example.guideline.service;

import com.example.guideline.dto.StepRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface StepService {
    public ResponseEntity<?> getAllSteps();
    public ResponseEntity<?> getStepById(String id);
    public ResponseEntity<?> createStep(MultipartFile image, StepRequestDto stepRequestDto);
    public ResponseEntity<?> updateStep(String id, MultipartFile image, StepRequestDto stepRequestDto);
    public ResponseEntity<?> deleteStep(String id);
}
