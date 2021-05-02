package com.mini.pilot.springboot.domain.sktservice;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SktserviceRepository extends JpaRepository<Sktservice,Long> {

    @Query("SELECT p FROM Sktservice p ORDER BY p.id DESC")
    List<Sktservice> findAllDesc();
}
