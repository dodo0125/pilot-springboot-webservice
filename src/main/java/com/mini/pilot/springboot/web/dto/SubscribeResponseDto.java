package com.mini.pilot.springboot.web.dto;

import com.mini.pilot.springboot.domain.customer.Customer;
import com.mini.pilot.springboot.domain.product.Subscribe;
import lombok.Getter;

@Getter
public class SubscribeResponseDto {

    private Long productid;
    private Long serviceid;
    private String startdate;
    private String enddate;


    public SubscribeResponseDto(Subscribe entity){
        this.productid = entity.getProductid();
        this.serviceid = entity.getServiceid();
        this.startdate = entity.getStartdate();
        this.enddate = entity.getEnddate();
    }

}
