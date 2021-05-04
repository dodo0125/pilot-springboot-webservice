package com.mini.pilot.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDto {
    private String productname;
    private int fee;

    @Builder
    public ProductUpdateRequestDto(String productname, int fee){
        this.productname= productname;
        this.fee = fee;
    }

}
