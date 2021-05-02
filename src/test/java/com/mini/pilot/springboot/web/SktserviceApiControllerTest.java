package com.mini.pilot.springboot.web;

import com.mini.pilot.springboot.domain.customer.Customer;
import com.mini.pilot.springboot.domain.customer.CustomerRepository;
import com.mini.pilot.springboot.domain.sktservice.Sktservice;
import com.mini.pilot.springboot.domain.sktservice.SktserviceRepository;
import com.mini.pilot.springboot.web.dto.*;
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

public class SktserviceApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SktserviceRepository sktserviceRepository ;

    @AfterEach
    public void tearDown() throws Exception {
        sktserviceRepository.deleteAll();
    }

    @Test
    public void Sktservice_등록된다() throws Exception{
        //given
        String svcnum = "svcnum";
        Long customerid = Long.valueOf(0);

        SktserviceSaveRequestDto requestDto = SktserviceSaveRequestDto.builder()
            .svcnum(svcnum)
            .customerid(customerid)
            .build();

        String url = "http://localhost:"+port+"/api/v1/sktservice" ;

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);


        List<Sktservice> all = sktserviceRepository.findAll();
        assertThat(all.get(0).getSvcnum()).isEqualTo((svcnum));
        assertThat(all.get(0).getCustomerid()).isEqualTo(customerid) ;

    }

    @Test
    public void Sktservice_수정된다() throws Exception {
        //given
        Sktservice savedSktservice = sktserviceRepository.save(Sktservice.builder()
                .svcnum("svcnum")
                .customerid(Long.valueOf(0))
                .build());


        Long updateId = savedSktservice.getId();
        String expectedSvcnum = "010";

        SktserviceUpdateRequestDto requestDto = SktserviceUpdateRequestDto.builder()
                .svcnum(expectedSvcnum)
                .build() ;

        String url = "http://localhost:"+port+"/api/v1/sktservice/"+ updateId ;

        HttpEntity<SktserviceUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Sktservice> all = sktserviceRepository.findAll();

        assertThat(all.get(0).getSvcnum()).isEqualTo(expectedSvcnum);

    }

}
