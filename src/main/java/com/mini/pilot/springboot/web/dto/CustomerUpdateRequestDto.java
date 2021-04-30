package com.mini.pilot.springboot.web.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerUpdateRequestDto {
    private String customername;
    private int age;

    @Builder
    public CustomerUpdateRequestDto(String customername, int age){
        this.customername= customername;
        this.age = age;
    }
}
