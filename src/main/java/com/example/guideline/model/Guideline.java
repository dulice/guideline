package com.example.guideline.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Guideline {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String titleMm;
    private String subtitle;
    private String subtitleMm;
    private String deepLink;

    @Enumerated(EnumType.STRING)
    private GuidelineTypeEnum guidelineType;

    private String image;

    @OneToMany(mappedBy = "guideline", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GuidelineStep> steps = new ArrayList<>();

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

}
