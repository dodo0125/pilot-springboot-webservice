package com.mini.pilot.springboot.domain.sktservice;


import com.mini.pilot.springboot.domain.sktservice.Sktservice;
import com.mini.pilot.springboot.domain.sktservice.SktserviceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class) //junit4에서는 @RunWith(SpringRunner.class)
//별다른 설정없이 @SpringBootTest를 사용할 경우, H2 데이터베이스를 자동으로 실행해줌
@SpringBootTest
public class SktserviceRepositoryTest {

    @Autowired

    SktserviceRepository sktserviceRepository;

    // Junit에서는 단위테스트가 끝날 때마다 수행되는 메소드를 지정,
    // 테스트용 db인 h2에 데이터가 그대로 남아 있어 다음 테스트 실행시 테스트가 실패할 수 있음
    @AfterEach // junit4에서는 @After
    public void cleanup(){
        sktserviceRepository.deleteAll();
    }

    @Test
    public void 서비스정보_불러오기(){
        //given
        String svcnum = "010-xxxx-xxxx";
        Long customerid = Long.valueOf(0);

        //테이블 sktservice에 insert/update 쿼리 실행. id값이 있으면 update, 없으면 insert 쿼리 실행
        sktserviceRepository.save(Sktservice.builder()
                .svcnum(svcnum)
                .customerid(customerid)
                .build()) ;

        //when
        //sktserviceRepository.findAll() : 테이블 posts에 있는 모든 데이터들을 조회해 오는 메소드
        List<Sktservice> SktserviceList = sktserviceRepository.findAll() ;

        //then
        Sktservice sktservice = SktserviceList.get(0);
        assertThat(sktservice.getSvcnum()).isEqualTo(svcnum);
        assertThat(sktservice.getCustomerid()).isEqualTo(customerid);


    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        sktserviceRepository.save(Sktservice.builder()
                .svcnum("svcnum")
                .customerid(Long.valueOf(0))
                .build());

        //when
        List<Sktservice> sktserviceList = sktserviceRepository.findAll();

        //then
        Sktservice sktservice = sktserviceList.get(0);

        System.out.println(">>>>>>>>>>>> create Date = " +sktservice.getCreatedDate() + ", modifiedDtae = " +sktservice.getModifiedDate());

        assertThat(sktservice.getCreatedDate()).isAfter(now);
        assertThat(sktservice.getModifiedDate()).isAfter(now);

    }

}
