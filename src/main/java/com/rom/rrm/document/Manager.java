package com.rom.rrm.document;

import com.rom.rrm.util.Designation;
import com.rom.rrm.util.Gender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Manager extends BaseEntity {

    @Id
    private String id;
    private String companyId;
    @TextIndexed
    private String name;
    private Gender gender;
    private Designation designation;
    private int totalRatings;
    private int totalReviews;
}
