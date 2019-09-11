package com.rom.rrm.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document
@Getter
@Setter
@CompoundIndex(def = "{'managerId':1, 'name':1, 'fingerprint':1}", name = "c_i_review", unique = true)
public class Review extends BaseEntity {
    @Id
    private String id;
    @NotBlank
    private String managerId;
    private String name;
    @NotNull
    private @Valid Feedback feedback;
    private int overallRating;
    @NotNull
    private Boolean isRecommended;
    @Min(1)
    @Max(3)
    private int reviewerRelation;
    @NotBlank
    private String reviewerExperience;
    private String comments;
    @NotBlank
    private String fingerprint;
    private int helpful;
    private int notHelpful;
}
