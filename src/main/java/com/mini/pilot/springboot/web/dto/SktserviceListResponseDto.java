package com.mini.pilot.springboot.web.dto;

import com.mini.pilot.springboot.domain.sktservice.Sktservice;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SktserviceListResponseDto {
    private Long id;
    private String svcnum;
    private Long customerid;
    private LocalDateTime modifiedDate;

    public SktserviceListResponseDto(Sktservice entity){
        this.id = entity.getId();
        this.svcnum = entity.getSvcnum();
        this.customerid = entity.getCustomerid();
        this.modifiedDate = entity.getModifiedDate();
    }
}
