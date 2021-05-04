package com.mini.pilot.springboot.web.dto;


import com.mini.pilot.springboot.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSaveRequestDto {

    private String productname;
    private int fee;

    @Builder
    public ProductSaveRequestDto(String productname, int fee){
        this.productname = productname;
        this.fee = fee;
    }

    public Product toEntity(){
        return Product.builder()
                .productname(productname)
                .fee(fee)
                .build();
    }

}
