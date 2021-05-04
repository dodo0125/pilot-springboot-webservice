package com.mini.pilot.springboot.web.dto;

import com.mini.pilot.springboot.domain.product.Product;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductListResponseDto {

    private Long productid;
    private String productname;
    private int fee;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public ProductListResponseDto(Product entity){
        this.productid = entity.getProductid();
        this.productname = entity.getProductname();
        this.fee = entity.getFee();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();

    }
}
