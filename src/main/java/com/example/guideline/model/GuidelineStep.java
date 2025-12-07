package com.example.guideline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "GUIDELINE_STEP")
public class GuidelineStep {

    @EmbeddedId
    private GuidelineStepId id = new GuidelineStepId();

    @ManyToOne
    @MapsId("guidelineId")
    @JsonIgnore
    private Guideline guideline;

    @ManyToOne
    @MapsId("stepId")
    @JsonIgnore
    private Step step;

    private int no;
}
