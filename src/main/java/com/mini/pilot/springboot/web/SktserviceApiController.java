package com.mini.pilot.springboot.web;

import com.mini.pilot.springboot.service.SktserviceService;
import com.mini.pilot.springboot.web.dto.SktserviceListResponseDto;
import com.mini.pilot.springboot.web.dto.SktserviceResponseDto;
import com.mini.pilot.springboot.web.dto.SktserviceSaveRequestDto;
import com.mini.pilot.springboot.web.dto.SktserviceUpdateRequestDto;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SktserviceApiController {
    private final SktserviceService sktserviceService;

    @PostMapping("/api/v1/sktservice")
    public Long save(@RequestBody SktserviceSaveRequestDto requestDto){
        return sktserviceService.save(requestDto) ;
    }

    @PutMapping("/api/v1/sktservice/{id}")
    public  Long update(@PathVariable Long id, @RequestBody SktserviceUpdateRequestDto requestDto){
        return sktserviceService.update(id, requestDto);
    }

    @GetMapping("/api/v1/sktservice/{id}")
    public SktserviceResponseDto findById(@PathVariable Long id){
        return sktserviceService.findById(id);
    }

    @GetMapping("/api/v1/sktservice/list")
    public List<SktserviceListResponseDto> findAll() {
        return sktserviceService.findAllDesc();
    }

    @DeleteMapping("/api/v1/sktservice/{id}")
    public Long delete(@PathVariable Long id){
        sktserviceService.delete(id);
        return id;
    }

}
