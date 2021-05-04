package com.mini.pilot.springboot.web;

import com.mini.pilot.springboot.domain.customer.Customer;
import com.mini.pilot.springboot.domain.customer.CustomerQueryRepository;
import com.mini.pilot.springboot.domain.customer.CustomerRepository;
import com.mini.pilot.springboot.domain.product.*;
import com.mini.pilot.springboot.web.dto.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //Random Port 실행
public class ProductApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SubscribeRepository subscribeRepository;
    @Autowired
    private SubscribeQueryRepository subscribeQueryRepository;

    @AfterEach
    public void tearDown() throws Exception {
        productRepository.deleteAll();
    }


    @Test
    public void Product_등록된다() throws Exception{
        //given
        String productname = "productname";
        int fee = 0;
        ProductSaveRequestDto requestDto = ProductSaveRequestDto.builder()
                .productname(productname)
                .fee(fee)
                .build();

        String url = "http://localhost:"+port+"/api/v1/product" ;

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Product> all = productRepository.findAll();
        assertThat(all.get(0).getProductname()).isEqualTo(productname);
        assertThat(all.get(0).getFee()).isEqualTo(fee);

    }


    @Test
    public void Product_수정된다() throws Exception {
        //given
        Product savedProduct = productRepository.save(Product.builder()
                .productname("productname")
                .fee(0)
                .build());

        Long updateId = savedProduct.getProductid();
        String expectedProductName = "특별상품";
        int expectedfee = 10000 ;

        ProductUpdateRequestDto requestDto = ProductUpdateRequestDto.builder()
                .productname(expectedProductName)
                .fee(expectedfee)
                .build() ;

        String url = "http://localhost:"+port+"/api/v1/product/"+ updateId ;

        HttpEntity<ProductUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Product> all = productRepository.findAll();

        assertThat(all.get(0).getProductname()).isEqualTo(expectedProductName);
        assertThat(all.get(0).getFee()).isEqualTo(expectedfee);
    }

    @Test
    public void Subscribe_등록된다() throws Exception{
        //given
        Long productid = Long.valueOf(1) ;
        Long serviceid = Long.valueOf(1) ;

        SubscribeSaveRequestDto requestDto = SubscribeSaveRequestDto.builder()
                .productid(productid)
                .serviceid(serviceid)
                .build();

        String url = "http://localhost:"+port+"/api/v1/subscribe" ;

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Subscribe> all = subscribeRepository.findAll();
        assertThat(all.get(0).getProductid()).isEqualTo(productid);
        assertThat(all.get(0).getServiceid()).isEqualTo(serviceid);

    }


    @Test
    public void Subscribe_조회된다() throws Exception{
        //given
        Long productid = Long.valueOf(1);
        Long serviceid = Long.valueOf(1);
        SubscribeSaveRequestDto requestDto = SubscribeSaveRequestDto.builder()
                .productid(productid)
                .serviceid(serviceid)
                .build();

        String url = "http://localhost:"+port+"/api/v1/subscribe" ;


        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Subscribe> all = subscribeQueryRepository.findByServiceId(serviceid);
        assertThat(all.get(0).getProductid()).isEqualTo(productid);
        assertThat(all.get(0).getServiceid()).isEqualTo(serviceid);

        System.out.println("---------------------------테스트 결과---------------------------");
        System.out.println("테스트 결과 : "+ all.get(0).toString());
        System.out.println("테스트 결과 getProductid : "+ all.get(0).getProductid());
        System.out.println("테스트 결과 getServiceid : "+ all.get(0).getServiceid());

    }

}
