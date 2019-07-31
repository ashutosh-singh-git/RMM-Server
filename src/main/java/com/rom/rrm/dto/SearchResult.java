package com.rom.rrm.dto;

import com.rom.rrm.util.Designation;
import com.rom.rrm.util.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchResult {

    private String id;
    private String managerName;
    private Gender gender;
    private Designation designation;
    private String companyId;
    private String companyName;
    private String city;
    private int totalReviews;
    private int averageRating;
    private PromotedReview promotedReview;
}
