package com.mini.pilot.springboot.web;

import com.mini.pilot.springboot.domain.product.Subscribe;
import com.mini.pilot.springboot.service.ProductService;
import com.mini.pilot.springboot.service.SubscribeService;
import com.mini.pilot.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ProductApiController {

    private final ProductService productService;
    private final SubscribeService subscribeService;

    @PostMapping("/api/v1/product")
    public Long save(@RequestBody ProductSaveRequestDto requestDto){
        return productService.save(requestDto);
    }

    @PutMapping("/api/v1/product/{productid}")
    public Long update(@PathVariable Long productid, @RequestBody ProductUpdateRequestDto requestDto){
        return productService.update(productid,requestDto);
    }

    @GetMapping("/api/v1/product/{productid}")
    public ProductResponseDto findById(@PathVariable Long productid){
        return productService.findById(productid);
    }

    @GetMapping("/api/v1/product/list")
    public List<ProductListResponseDto> findAll(){
        return productService.findAllDesc();
    }

    @DeleteMapping("/api/v1/product/{productid}")
    public Long delete(@PathVariable Long productid ){
        productService.delete(productid);
        return productid;
    }

    // 여기서 부터는 subscribe --------------------------------------------------------------------------------

    @PostMapping("/api/v1/subscribe")
    public Long save(@RequestBody SubscribeSaveRequestDto requestDto){
        return subscribeService.save(requestDto);
    }

    @PutMapping("/api/v1/subscribe/{id}")
    public Long update(@PathVariable Long id ){
        return subscribeService.update(id);
    }


    @GetMapping("/api/v1/subscribe/{id}")
    public SubscribeResponseDto findSubscribeById(@PathVariable Long id){
        return subscribeService.findById(id);
    }


    @GetMapping("/api/v1/subscribe/list")
    public List<SubscribeListResponseDto> findSubscribeAll(){
        return subscribeService.findAllDesc();
    }

    @DeleteMapping("/api/v1/subscribe/{id}")
    public Long deletesubscribe(@PathVariable Long id ){
        subscribeService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/subscribe/{serviceid}/search")
    public List<SubscribeInfoListResponseDto>  findByServiceId(@PathVariable Long serviceid){
        return subscribeService.findSubscribeInfoByServiceId(serviceid);
    }


}
