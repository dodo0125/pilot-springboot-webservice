package com.mini.pilot.springboot.web.dto;


import com.mini.pilot.springboot.domain.customer.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerSaveRequestDto {
    private String customername;
    private int age;

    @Builder
    public CustomerSaveRequestDto(String customername, int age){
        this.customername = customername;
        this.age = age;
    }

    public Customer toEntity(){
        return Customer.builder()
                .customername(customername)
                .age(age)
                .build();
    }
}
