package com.mini.pilot.springboot.web.dto;


import com.mini.pilot.springboot.domain.customer.Customerinfo;
import lombok.Getter;

@Getter
public class CustomerInfoListResponseDto {
    private String customername;
    private int age;
    private String svcnum;
    private Long customerid;

    public CustomerInfoListResponseDto(Customerinfo entity){
        this.customername = entity.getCustomername();
        this.age = entity.getAge();
        this.svcnum = entity.getSvcnum();
        this.customerid = entity.getCustomerid();

    }
}
