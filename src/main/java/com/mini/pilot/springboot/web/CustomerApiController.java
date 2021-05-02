package com.mini.pilot.springboot.web;


import com.mini.pilot.springboot.service.CustomerService;
import com.mini.pilot.springboot.web.dto.*;
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


    @GetMapping("/api/v1/customer/{customername}/search")
    public List<CustomerListResponseDto>  findByName(@PathVariable String customername){
        return customerService.findByName(customername);
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

    @GetMapping("/api/v1/customer/{customername}/searchinfo")
    public List<CustomerInfoListResponseDto>  findCustomerinfoByName(@PathVariable String customername){
        return customerService.findCustomerinfoByName(customername);
    }

}
