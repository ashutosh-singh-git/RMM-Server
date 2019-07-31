package com.rom.rrm.dto;

import com.rom.rrm.document.Review;
import com.rom.rrm.util.Designation;
import com.rom.rrm.util.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ReviewResult {

    private String id;
    private String managerName;
    private Gender gender;
    private Designation designation;
    private String companyId;
    private String companyName;
    private String city;
    private int totalReviews;
    private int averageRating;
    private List<Review> reviews;
}
