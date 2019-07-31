package com.rom.rrm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PromotedReview {

    private String reviewer;
    private int rating;
    private String comments;
}
