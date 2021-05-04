package com.mini.pilot.springboot.service;

import com.mini.pilot.springboot.domain.customer.Customer;
import com.mini.pilot.springboot.domain.product.Product;
import com.mini.pilot.springboot.domain.product.Subscribe;
import com.mini.pilot.springboot.domain.product.SubscribeQueryRepository;
import com.mini.pilot.springboot.domain.product.SubscribeRepository;
import com.mini.pilot.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final SubscribeQueryRepository subscribeQueryRepository;

    @Transactional
    public Long save(SubscribeSaveRequestDto requestDto){
        return subscribeRepository.save(requestDto.toEntity()).getProductid();
    }

    @Transactional
    public Long update(Long id){
        Subscribe subscribe = subscribeRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 가입이력이 없습니다. id ="+id));
        subscribe.update();

        return id;
    }

    @Transactional(readOnly = true)
    public SubscribeResponseDto findById(Long id){
        Subscribe entity = subscribeRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 가입이력이 없습니다. id="+id));
        return new SubscribeResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<SubscribeListResponseDto> findAllDesc(){
        return subscribeRepository.findAllDesc().stream().map(SubscribeListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Subscribe subscribe = subscribeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 가입이력이 없습니다. id = " + id));
        subscribeRepository.delete(subscribe);
    }

    @Transactional(readOnly = true)
    public List<SubscribeListResponseDto> findByServiceId(Long serviceid){
        return subscribeQueryRepository.findByServiceId(serviceid).stream().map(SubscribeListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SubscribeInfoListResponseDto> findSubscribeInfoByServiceId(Long serviceid){
        return subscribeQueryRepository.findSubscribeInfoByServiceId(serviceid).stream().map(SubscribeInfoListResponseDto::new).collect(Collectors.toList());
    }

}
