package com.mini.pilot.springboot.domain.sktservice;


import com.mini.pilot.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Sktservice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String svcnum;

    @Column(length = 100, nullable = false)
    private Long customerid ;

    @Builder
    public Sktservice(String svcnum, Long customerid){
        this.svcnum = svcnum;
        this.customerid = customerid;
    }

    public void update(String svcnum){
        this.svcnum = svcnum;
    }




}
