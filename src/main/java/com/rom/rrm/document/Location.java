package com.rom.rrm.document;

import com.rom.rrm.util.GeoLocation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@Builder
public class Location extends BaseEntity {

    @Id
    private String id;
    private String address;
    private GeoLocation geo;

}
