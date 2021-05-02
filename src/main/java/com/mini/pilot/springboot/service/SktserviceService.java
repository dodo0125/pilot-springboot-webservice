package com.mini.pilot.springboot.service;

import com.mini.pilot.springboot.domain.sktservice.Sktservice;
import com.mini.pilot.springboot.domain.sktservice.SktserviceRepository;
import com.mini.pilot.springboot.web.dto.SktserviceListResponseDto;
import com.mini.pilot.springboot.web.dto.SktserviceResponseDto;
import com.mini.pilot.springboot.web.dto.SktserviceSaveRequestDto;
import com.mini.pilot.springboot.web.dto.SktserviceUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SktserviceService {
    private final SktserviceRepository sktserviceRepository;

    @Transactional
    public Long save(SktserviceSaveRequestDto requestDto){
        return sktserviceRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, SktserviceUpdateRequestDto requestDto){
        Sktservice sktservice = sktserviceRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 서비스가 없습니다. id "+id)) ;
        sktservice.update(requestDto.getSvcnum());

        return id;
    }

    @Transactional(readOnly = true)
    public SktserviceResponseDto findById(Long id){
        Sktservice entity = sktserviceRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 서비스가 없습니다. id= "+id));
        return new SktserviceResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<SktserviceListResponseDto> findAllDesc(){
        return sktserviceRepository.findAllDesc().stream().map(SktserviceListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Sktservice sktservice = sktserviceRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 서비스가 없습니다. id = " + id));
        sktserviceRepository.delete(sktservice);
    }

}
