package com.rom.rrm.document;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
class BaseEntity {

    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

}
