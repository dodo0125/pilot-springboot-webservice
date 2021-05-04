package com.mini.pilot.springboot.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe,Long> {


    @Query("SELECT p FROM Subscribe p ORDER BY p.productid DESC")
    List<Subscribe> findAllDesc();


}
