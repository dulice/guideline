package com.example.guideline.repository;

import com.example.guideline.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepRepository extends JpaRepository<Step,String> {
}
