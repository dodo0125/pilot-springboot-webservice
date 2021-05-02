package com.mini.pilot.springboot.web;

import com.mini.pilot.springboot.domain.customer.Customer;
import com.mini.pilot.springboot.domain.customer.CustomerQueryRepository;
import com.mini.pilot.springboot.domain.customer.CustomerRepository;
import com.mini.pilot.springboot.domain.customer.Customerinfo;
import com.mini.pilot.springboot.web.dto.CustomerInfoListResponseDto;
import com.mini.pilot.springboot.web.dto.CustomerSaveRequestDto;
import com.mini.pilot.springboot.web.dto.CustomerUpdateRequestDto;
import com.mini.pilot.springboot.web.dto.SktserviceSaveRequestDto;
import lombok.EqualsAndHashCode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.NotExtensible;
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

public class CustomerApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerQueryRepository customerQueryRepository;

    @AfterEach
    public void tearDown() throws Exception {
        customerRepository.deleteAll();
    }

    @Test
    public void Customer_등록된다() throws Exception{
        //given
        String customername = "customername";
        int age = 0;
        CustomerSaveRequestDto requestDto = CustomerSaveRequestDto.builder()
                .customername(customername)
                .age(age)
                .build();

        String url = "http://localhost:"+port+"/api/v1/customer" ;

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Customer> all = customerRepository.findAll();
        assertThat(all.get(0).getCustomername()).isEqualTo(customername);
        assertThat(all.get(0).getAge()).isEqualTo(age);

    }

    @Test
    public void Customer_수정된다() throws Exception {
        //given
        Customer savedCustomer = customerRepository.save(Customer.builder()
                .customername("customername")
                .age(0)
                .build());

        Long updateId = savedCustomer.getId();
        String expectedName = "홍길동";
        int expectedAge = 0 ;

        CustomerUpdateRequestDto requestDto = CustomerUpdateRequestDto.builder()
                .customername(expectedName)
                .age(expectedAge)
                .build() ;

        String url = "http://localhost:"+port+"/api/v1/customer/"+ updateId ;

        HttpEntity<CustomerUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Customer> all = customerRepository.findAll();

        assertThat(all.get(0).getCustomername()).isEqualTo(expectedName);
        assertThat(all.get(0).getAge()).isEqualTo(0);
    }


    @Test
    public void Customer_조회된다() throws Exception{
        //given
        String customername = "홍길동";
        int age = 10;
        CustomerSaveRequestDto requestDto = CustomerSaveRequestDto.builder()
                .customername(customername)
                .age(age)
                .build();

        String url = "http://localhost:"+port+"/api/v1/customer" ;


        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Customer> all = customerQueryRepository.findByName(customername);
        assertThat(all.get(0).getCustomername()).isEqualTo(customername);
        assertThat(all.get(0).getAge()).isEqualTo(age);

        System.out.println("---------------------------테스트 결과---------------------------");
        System.out.println("테스트 결과 : "+ all.get(0).toString());
        System.out.println("테스트 결과 Age : "+ all.get(0).getAge());
        System.out.println("테스트 결과 Customername : "+ all.get(0).getCustomername());
    }

    @Test
    public void Customer_info_조회된다() throws Exception{
        //given
        String customername = "홍길동";
        int age = 10;
        String svcnum = "010123456789" ;
        Long customerid = Long.valueOf(1) ;

        CustomerSaveRequestDto requestDto = CustomerSaveRequestDto.builder()
                .customername(customername)
                .age(age)
                .build();

        SktserviceSaveRequestDto requestDto1 = SktserviceSaveRequestDto.builder()
                .svcnum(svcnum)
                .customerid(customerid)
                .build();

        String url = "http://localhost:"+port+"/api/v1/customer" ;
        String url1 = "http://localhost:"+port+"/api/v1/sktservice" ;

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);
        ResponseEntity<Long> responseEntity1 = restTemplate.postForEntity(url1,requestDto1,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        assertThat(responseEntity1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity1.getBody()).isGreaterThan(0L);


        List<Customerinfo> all = customerQueryRepository.findCustomerinfoByName(customername);
        assertThat(all.get(0).getCustomername()).isEqualTo(customername);
        assertThat(all.get(0).getAge()).isEqualTo(age);

        System.out.println("---------------------------테스트 결과---------------------------");
        System.out.println("테스트 결과 : "+ all.get(0).toString());
        System.out.println("테스트 결과 Age : "+ all.get(0).getAge());
        System.out.println("테스트 결과 Customername : "+ all.get(0).getCustomername());
        System.out.println("테스트 결과 Svcnum : "+ all.get(0).getSvcnum());
        System.out.println("테스트 결과 CustomerId : "+ all.get(0).getCustomerid());
    }

}
