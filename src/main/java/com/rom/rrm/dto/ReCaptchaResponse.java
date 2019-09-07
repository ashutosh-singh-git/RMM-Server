package com.rom.rrm.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReCaptchaResponse {

    boolean success;
    String hostname;
    String score;
    String challenge_ts;
}

