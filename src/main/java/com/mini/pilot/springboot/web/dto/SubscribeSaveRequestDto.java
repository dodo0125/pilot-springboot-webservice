package com.mini.pilot.springboot.web.dto;

import com.mini.pilot.springboot.domain.product.Subscribe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubscribeSaveRequestDto {

    private Long productid;
    private Long serviceid;
    private String startdate;
    private String enddate;

    @Builder
    public SubscribeSaveRequestDto(Long productid, Long serviceid){
        this.productid = productid;
        this.serviceid = serviceid;
    }

    public Subscribe toEntity(){
        return Subscribe.builder()
                .productid(productid)
                .serviceid(serviceid)
                .build();
    }



}
