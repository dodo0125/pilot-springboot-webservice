package com.mini.pilot.springboot.domain.customer;

import com.mini.pilot.springboot.domain.sktservice.Sktservice;
import com.mini.pilot.springboot.domain.sktservice.SktserviceRepository;
import org.apache.catalina.util.CustomObjectInputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.CoreMatchers.is;


@ExtendWith(SpringExtension.class) //junit4에서는 @RunWith(SpringRunner.class)
//별다른 설정없이 @SpringBootTest를 사용할 경우, H2 데이터베이스를 자동으로 실행해줌
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SktserviceRepository sktserviceRepository;
    @Autowired
    CustomerQueryRepository customerQueryRepository;

    // Junit에서는 단위테스트가 끝날 때마다 수행되는 메소드를 지정,
    // 테스트용 db인 h2에 데이터가 그대로 남아 있어 다음 테스트 실행시 테스트가 실패할 수 있음
    @AfterEach // junit4에서는 @After
    public void cleanup(){
        customerRepository.deleteAll();
    }

    @Test
    public void 고객정보_불러오기(){
        //given
        String customername = "홍길동";
        int age = 0 ;

        //테이블 posts에 insert/update 쿼리 실행. id값이 있으면 update, 없으면 insert 쿼리 실행
        customerRepository.save(Customer.builder()
                .customername(customername)
                .age(age)
                .build()) ;

        //when
        //postsRepository.findAll() : 테이블 posts에 있는 모든 데이터들을 조회해 오는 메소드
        List<Customer> customerList = customerRepository.findAll() ;

        //then
        Customer customer = customerList.get(0);
        assertThat(customer.getCustomername()).isEqualTo(customername);
        assertThat(customer.getAge()).isEqualTo(age);


    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        customerRepository.save(Customer.builder()
                .customername("customername")
                .age(0)
                .build());

        //when
        List<Customer> customerList = customerRepository.findAll();

        //then
        Customer customer = customerList.get(0);

        System.out.println(">>>>>>>>>>>> create Date = " +customer.getCreatedDate() + ", modifiedDtae = " +customer.getModifiedDate());

        assertThat(customer.getCreatedDate()).isAfter(now);
        assertThat(customer.getModifiedDate()).isAfter(now);

    }

    @Test
    public void querydsl_기본_기능_확인2() {
        //given
        String customername = "홍길동";
        int age = 10;
        customerRepository.save(new Customer(customername, age));
        //when
        Customer customer = customerQueryRepository.findByName(customername).get(0);

        //then
        assertThat(customer.getCustomername()).isEqualTo(customername);
        assertThat(customer.getAge()).isEqualTo(age);


    }

    @Test
    public void 관계없을때_조인_맺기(){
        //given
        String customername = "홍길동";
        int age = 10;
        customerRepository.save(new Customer(customername, age));

        String svcnum = "01012345678" ;
        Long customerid = Long.valueOf(1);
        sktserviceRepository.save(new Sktservice(svcnum, customerid)) ;

        Customerinfo customerinfo = customerQueryRepository.findCustomerinfoByName("customername").get(0) ;

        //then
        assertThat(customerinfo.getCustomername()).isEqualTo(customername);
        assertThat(customerinfo.getAge()).isEqualTo(age);
        assertThat(customerinfo.getSvcnum()).isEqualTo(svcnum);
        assertThat(customerinfo.getCustomername()).isEqualTo(customername);

    }


}

