package com.mini.pilot.springboot.service;

import com.mini.pilot.springboot.domain.customer.Customer;
import com.mini.pilot.springboot.domain.product.Product;
import com.mini.pilot.springboot.domain.product.ProductRepository;
import com.mini.pilot.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;


    // 신규 상품 등록
    @Transactional
    public Long save(ProductSaveRequestDto requestDto){
        return productRepository.save(requestDto.toEntity()).getProductid();
    }

    //상품이름 수정(update)
    @Transactional
    public Long update(Long productid, ProductUpdateRequestDto requestDto){
        Product product = productRepository.findById(productid).orElseThrow(()-> new IllegalArgumentException("해당 고객이 없습니다. productid ="+productid));
        product.update(requestDto.getProductname(), requestDto.getFee());

        return productid;
    }

    //상품 id로 상품 data 가져오기
    @Transactional(readOnly = true)
    public ProductResponseDto findById(Long productid){
        Product entity = productRepository.findById(productid).orElseThrow(()-> new IllegalArgumentException("해당 상품이 없습니다. productid = "+productid));
        return new ProductResponseDto(entity);
    }


    @Transactional(readOnly = true)
    public List<ProductListResponseDto> findAllDesc(){
        return productRepository.findAllProductDesc().stream().map(ProductListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long productid){
        Product product = productRepository.findById(productid).orElseThrow(()->new IllegalArgumentException("해당 상품이 없습니다. productid = "+productid));
        productRepository.delete(product);
    }




}
