package com.rom.rrm.dto;

import com.rom.rrm.util.Designation;
import com.rom.rrm.util.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewManager {
    private String managerName;
    private Gender gender;
    private Designation designation;
    private String companyId;
    private String companyName;
    private String city;
}
