package com.mini.pilot.springboot.web.dto;

import com.mini.pilot.springboot.domain.product.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private Long productid;
    private String productname ;
    private int fee;

    public ProductResponseDto(Product entity){
        this.productid = entity.getProductid();
        this.productname = entity.getProductname();
        this.fee = entity.getFee();
    }
}
