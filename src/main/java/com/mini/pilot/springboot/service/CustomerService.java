package com.mini.pilot.springboot.service;

import com.mini.pilot.springboot.domain.customer.Customer;
import com.mini.pilot.springboot.domain.customer.CustomerRepository;
import com.mini.pilot.springboot.web.dto.CustomerListResponseDto;
import com.mini.pilot.springboot.web.dto.CustomerResponseDto;
import com.mini.pilot.springboot.web.dto.CustomerSaveRequestDto;
import com.mini.pilot.springboot.web.dto.CustomerUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Long save(CustomerSaveRequestDto requestDto){
        return customerRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, CustomerUpdateRequestDto requestDto){
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 고객이 없습니다. id ="+id));
        customer.update(requestDto.getCustomername(), requestDto.getAge());

        return id;
    }

    @Transactional(readOnly = true)
    public CustomerResponseDto findById(Long id){
        Customer entity = customerRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 고객이 없습니다. id="+id));
        return new CustomerResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<CustomerListResponseDto> findAllDesc(){
        return customerRepository.findAllDesc().stream().map(CustomerListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 고객이 없습니다. id = " + id));
        customerRepository.delete(customer);
    }
}
