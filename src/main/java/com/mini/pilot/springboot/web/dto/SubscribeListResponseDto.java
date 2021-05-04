package com.mini.pilot.springboot.web.dto;

import com.mini.pilot.springboot.domain.customer.Customerinfo;
import com.mini.pilot.springboot.domain.product.Subscribe;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SubscribeListResponseDto {

    private Long productid;
    private Long serviceid;
    private String startdate;
    private String enddate;
    private LocalDateTime modifiedDate;


    public SubscribeListResponseDto(Subscribe entity){
        this.productid = entity.getProductid();
        this.serviceid = entity.getServiceid();
        this.startdate= entity.getStartdate();
        this.enddate = entity.getEnddate();
        this.modifiedDate = entity.getModifiedDate();

    }

}
