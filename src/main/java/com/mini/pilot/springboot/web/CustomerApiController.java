package com.mini.pilot.springboot.web;


import com.mini.pilot.springboot.service.CustomerService;
import com.mini.pilot.springboot.web.dto.CustomerListResponseDto;
import com.mini.pilot.springboot.web.dto.CustomerResponseDto;
import com.mini.pilot.springboot.web.dto.CustomerSaveRequestDto;
import com.mini.pilot.springboot.web.dto.CustomerUpdateRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CustomerApiController {

    private final CustomerService customerService;

    @PostMapping("/api/v1/customer")
    public Long save(@RequestBody CustomerSaveRequestDto requestDto){
        return customerService.save(requestDto);
    }

    @PutMapping("/api/v1/customer/{id}")
    public Long update(@PathVariable Long id, @RequestBody CustomerUpdateRequestDto requestDto){
        return customerService.update(id,requestDto);
    }

    @GetMapping("/api/v1/customer/{id}")
    public CustomerResponseDto findById(@PathVariable Long id){
        return customerService.findById(id);
    }

    @GetMapping("/api/v1/customer/list")
    public List<CustomerListResponseDto> findAll(){
        return customerService.findAllDesc();
    }

    @DeleteMapping("/api/v1/customer/{id}")
    public Long delete(@PathVariable Long id ){
        customerService.delete(id);
        return id;
    }

}
