package com.mini.pilot.springboot.web.dto;


import com.mini.pilot.springboot.domain.customer.Customer;
import lombok.Getter;

@Getter
public class CustomerResponseDto {
    private Long id;
    private String customername;
    private int age;

    public CustomerResponseDto(Customer entity){
        this.id = entity.getId();
        this.customername = entity.getCustomername();
        this.age = entity.getAge();
    }
}
