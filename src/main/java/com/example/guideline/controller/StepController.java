package com.example.guideline.controller;

import com.example.guideline.dto.StepRequestDto;
import com.example.guideline.service.StepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/v1/steps")
@RequiredArgsConstructor
public class StepController {

    private final StepService stepService;

    @GetMapping
    public ResponseEntity<?> getSteps() {
        return stepService.getAllSteps();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStepById(@PathVariable String id) {
        return stepService.getStepById(id);
    }

    @PostMapping
    public ResponseEntity<?> createStep(@RequestPart MultipartFile image, @RequestPart String stepRequestDto) {
        return stepService.createStep(image, convertToJson(stepRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStep(@PathVariable String id, @RequestPart MultipartFile image, @RequestPart String stepRequestDto) {
        return stepService.updateStep(id, image, convertToJson(stepRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStep(@PathVariable String id) {
        return stepService.deleteStep(id);
    }


    private StepRequestDto convertToJson(String requestObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(requestObject, StepRequestDto.class);
    }

}
