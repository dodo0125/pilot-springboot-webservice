package com.mini.pilot.springboot.domain.product;

import com.mini.pilot.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productid;

    @Column(length=500, nullable = false)
    private String productname;

    @Column(length=500, nullable = false)
    private int fee;

    // @Builder : 롬복의 어노테이션, 해당 클래스의 빌더 패턴 클래스 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Product(String productname, int fee){
        this.productname = productname ;
        this.fee = fee;
    }

    public void update(String productname, int fee){
        this.productname = productname;
        this.fee = fee;

    }
}
