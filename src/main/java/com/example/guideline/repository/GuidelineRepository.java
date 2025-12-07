package com.example.guideline.repository;

import com.example.guideline.model.Guideline;
import com.example.guideline.model.GuidelineTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuidelineRepository extends JpaRepository<Guideline,String> {
    List<Guideline> findByGuidelineType(GuidelineTypeEnum guidelineType);
}
