package com.rom.rrm.document;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class Feedback {

    @Min(1)
    @Max(5)
    private int behaviour;
    @Min(1)
    @Max(5)
    private int knowledge;
    @Min(1)
    @Max(5)
    private int skills;
    @Min(1)
    @Max(5)
    private int leadership;
    @Min(1)
    @Max(5)
    private int transparency;
    @Min(1)
    @Max(5)
    private int communication;
}
