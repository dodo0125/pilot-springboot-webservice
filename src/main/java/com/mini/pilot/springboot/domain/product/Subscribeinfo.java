package com.mini.pilot.springboot.domain.product;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Subscribeinfo {

    private Long productid;
    private Long serviceid;
    private String svcnum;
    private String startdate;
    private String enddate;


}
