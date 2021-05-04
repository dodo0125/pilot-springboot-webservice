package com.mini.pilot.springboot.domain.product;


import com.mini.pilot.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@Entity
public class Subscribe extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length=500, nullable = false)
    private Long productid;

    @Column(length=500, nullable = false)
    private Long serviceid ;


    @Column(length=500, nullable = false)
    private String startdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) ;

    @Column(length=500, nullable = false)
    private String enddate ="99991231" ;



    // @Builder : 롬복의 어노테이션, 해당 클래스의 빌더 패턴 클래스 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Subscribe(Long productid, Long serviceid){
        this.productid = productid ;
        this.serviceid = serviceid ;
    }

    public void update(){ //서비스상품 가입이력 update기능은 무조건 상품 해지기능으로만 쓰임 즉, eff_end_dt 꺾는 용도로만 사용
         this.enddate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) ;
    }
}
