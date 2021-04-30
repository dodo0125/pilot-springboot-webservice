package com.mini.pilot.springboot.domain.customer;

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
public class Customer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=500, nullable = false)
    private String customername;

    @Column(length=500, nullable = false)
    private int age;

    // @Builder : 롬복의 어노테이션, 해당 클래스의 빌더 패턴 클래스 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Customer(String customername, int age){
        this.customername = customername ;
        this.age = age ;
    }

    public void update(String customername, int age){
        this.customername = customername;
        this.age = age;
    }
}
