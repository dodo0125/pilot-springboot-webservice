package com.mini.pilot.springboot.web.dto;

import com.mini.pilot.springboot.domain.product.Subscribeinfo;
import lombok.Getter;

@Getter
public class SubscribeInfoListResponseDto {


    private Long productid;
    private Long serviceid;
    private String svcnum;
    private String startdate;
    private String enddate;

    public SubscribeInfoListResponseDto(Subscribeinfo entity) {
        this.productid = entity.getProductid();
        this.serviceid = entity.getServiceid();
        this.svcnum = entity.getSvcnum();
        this.startdate = entity.getStartdate();
        this.enddate = entity.getEnddate();

    }
}