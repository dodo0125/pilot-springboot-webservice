package com.mini.pilot.springboot.web.dto;


import com.mini.pilot.springboot.domain.sktservice.Sktservice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SktserviceSaveRequestDto {
    private String svcnum;
    private Long customerid;

    @Builder
    public SktserviceSaveRequestDto(String svcnum, Long customerid){
        this.svcnum = svcnum ;
        this.customerid = customerid;
    }

    public Sktservice toEntity(){
        return Sktservice.builder()
                .svcnum(svcnum)
                .customerid(customerid)
                .build();
    }
}
