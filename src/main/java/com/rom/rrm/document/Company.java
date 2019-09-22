package com.rom.rrm.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Document
@CompoundIndex(def = "{'name':1, 'city':1}", name = "c_i_company", unique = true)
public class Company extends BaseEntity {

    @Id
    private String id;
    @Indexed
    private String name;
    private Set<String> cities;

    public void addCity(String city) {
        if(cities != null){
            this.cities.add(city);
        }else {
            this.cities = new HashSet<>();
            this.cities.add(city);
        }
    }
}
