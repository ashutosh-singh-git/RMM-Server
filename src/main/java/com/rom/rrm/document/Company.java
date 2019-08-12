package com.rom.rrm.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
@CompoundIndex(def = "{'name':1, 'city':1}", name = "c_i_company", unique = true)
public class Company extends BaseEntity {

    @Id
    private String id;
    @Indexed
    private String name;
    private String city;
}
