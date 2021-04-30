package com.mini.pilot.springboot.web.dto;


import com.mini.pilot.springboot.domain.customer.Customer;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomerListResponseDto {
    private Long id;
    private String customername;
    private int age;
    private LocalDateTime modifiedDate;

    public CustomerListResponseDto(Customer entity){
        this.id = entity.getId();
        this.customername = entity.getCustomername();
        this.age = entity.getAge();
        this.modifiedDate = entity.getModifiedDate();
    }

}
