package com.mini.pilot.springboot.domain.product;

import jdk.internal.loader.AbstractClassLoaderValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class) //junit4에서는 @RunWith(SpringRunner.class)
//별다른 설정없이 @SpringBootTest를 사용할 경우, H2 데이터베이스를 자동으로 실행해줌
@SpringBootTest
public class SubscribeRepositoryTest {


    @Autowired
    SubscribeRepository subscribeRepository;

    // Junit에서는 단위테스트가 끝날 때마다 수행되는 메소드를 지정,
    // 테스트용 db인 h2에 데이터가 그대로 남아 있어 다음 테스트 실행시 테스트가 실패할 수 있음
    @AfterEach // junit4에서는 @After
    public void cleanup(){
        subscribeRepository.deleteAll();
    }


    @Test
    public void 상품정보_불러오기(){
        //given
        Long productid =  Long.valueOf(1);
        Long serviceid =  Long.valueOf(1);
        String enddate = "99991231";
        String startdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) ;

        subscribeRepository.save(Subscribe.builder()
                .productid(productid)
                .serviceid(serviceid)
                .build());

        //when
        //테이블 product에 있는 모든 데이터들을 조회해 오는 메소드
        List<Subscribe> subscribeList = subscribeRepository.findAll();

        //then
        Subscribe subscribe = subscribeList.get(0);
        assertThat(subscribe.getProductid()).isEqualTo(productid);
        assertThat(subscribe.getServiceid()).isEqualTo(serviceid);
        assertThat(subscribe.getEnddate()).isEqualTo(enddate);
        assertThat(subscribe.getStartdate()).isEqualTo(startdate);



    }


    @Test
    public void BaseTimeEntity_등록(){
        //given
        Long productid =  Long.valueOf(1);
        Long serviceid =  Long.valueOf(1);
        String enddate = "99991231";
        String startdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) ;

        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        subscribeRepository.save(Subscribe.builder()
                .productid(productid)
                .serviceid(serviceid)
                .build());

        //when
        List<Subscribe> subscribeList = subscribeRepository.findAll();

        //then
        Subscribe subscribe = subscribeList.get(0);

        System.out.println(">>>>>>>>>>>> create Date = " +subscribe.getCreatedDate() + ", modifiedDtae = " +subscribe.getModifiedDate());

        assertThat(subscribe.getCreatedDate()).isAfter(now);
        assertThat(subscribe.getModifiedDate()).isAfter(now);

    }

}
