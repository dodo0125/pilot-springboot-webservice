package com.mini.pilot.springboot.domain.product;

import com.mini.pilot.springboot.domain.customer.Customer;
import com.mini.pilot.springboot.domain.customer.Customerinfo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mini.pilot.springboot.domain.customer.QCustomer.customer;
import static com.mini.pilot.springboot.domain.product.QSubscribe.subscribe;
import static com.mini.pilot.springboot.domain.sktservice.QSktservice.sktservice;

@RequiredArgsConstructor
@Repository
public class SubscribeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Subscribe> findByServiceId(Long serviceid){
        return queryFactory.selectFrom(subscribe)
                .where(subscribe.serviceid.eq(serviceid))
                .fetch();

    }

    public List<Subscribeinfo> findSubscribeInfoByServiceId(Long serviceid){
        return queryFactory
                .select(Projections.fields(Subscribeinfo.class,
                        subscribe.productid.as("productid"),
                        subscribe.serviceid.as("serviceid"),
                        sktservice.svcnum.as("svcnum"),
                        subscribe.startdate.as("startdate"),
                        subscribe.enddate.as("enddate")
                ))
                .from(subscribe)
                .where(subscribe.serviceid.eq(serviceid))
                .innerJoin(sktservice).on(subscribe.serviceid.eq(sktservice.id))
                .fetch();
    }



}
