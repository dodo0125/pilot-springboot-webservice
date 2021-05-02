package com.mini.pilot.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SktserviceUpdateRequestDto {
    private String svcnum;

    @Builder
    public SktserviceUpdateRequestDto(String svcnum){
        this.svcnum = svcnum;
    }

}
