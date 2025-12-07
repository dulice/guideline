package com.example.guideline.controller;

import com.example.guideline.dto.GuidelineRequestDto;
import com.example.guideline.model.GuidelineTypeEnum;
import com.example.guideline.service.GuidelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/v1/guidelines")
@RequiredArgsConstructor
public class GuidelineController {

    private final GuidelineService guidelineService;

    @GetMapping
    public ResponseEntity<?> getGuidelines() {
        return guidelineService.getGuidelines();
    }

    @GetMapping("/type")
    public ResponseEntity<?> getGuidelinesByType(@RequestParam GuidelineTypeEnum guidelineType) {
        return guidelineService.getGuideline(guidelineType);
    }

    @PostMapping
    public ResponseEntity<?> createGuideline(@RequestPart MultipartFile image, @RequestPart String guidelineRequestDto) {
        return guidelineService.createGuideline(image, convertToDto(guidelineRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGuideline(@PathVariable String id, @RequestPart MultipartFile image, @RequestPart String guidelineRequestDto) {
        return guidelineService.updateGuideline(id, image, convertToDto(guidelineRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGuideline(@PathVariable String id) {
        return guidelineService.deleteGuideline(id);
    }

    private GuidelineRequestDto convertToDto(String requestObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(requestObject, GuidelineRequestDto.class);
    }
}

