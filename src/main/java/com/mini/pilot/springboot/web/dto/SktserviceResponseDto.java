package com.mini.pilot.springboot.web.dto;


import com.mini.pilot.springboot.domain.sktservice.Sktservice;
import lombok.Getter;

@Getter
public class SktserviceResponseDto {
    private Long id ;
    private String svcnum ;
    private Long customerid ;

    public SktserviceResponseDto(Sktservice entity){
        this.id = entity.getId();
        this.svcnum = entity.getSvcnum();
        this.customerid = entity.getCustomerid();
    }
}
