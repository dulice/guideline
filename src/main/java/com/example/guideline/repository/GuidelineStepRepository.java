package com.example.guideline.repository;

import com.example.guideline.model.GuidelineStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GuidelineStepRepository extends JpaRepository<GuidelineStep, String> {
    @Modifying
    @Query("DELETE FROM GuidelineStep gs WHERE gs.step.id = :stepId ")
    void deleteByStepId(String stepId);
}
