package com.mini.pilot.springboot.domain.customer;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.mini.pilot.springboot.domain.customer.QCustomer.customer;
import static com.mini.pilot.springboot.domain.sktservice.QSktservice.sktservice;

@RequiredArgsConstructor
@Repository
public class CustomerQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Customer> findByName(String customername){
        return queryFactory.selectFrom(customer)
                .where(customer.customername.eq(customername))
                .fetch();

    }

    public List<Customerinfo> findCustomerinfoByName(String customername){
        return queryFactory
                .select(Projections.fields(Customerinfo.class,
                        customer.customername.as("customername"),
                        customer.age.as("age"),
                        sktservice.svcnum.as("svcnum"),
                        sktservice.customerid.as("customerid")
                ))
                .from(customer)
                .where(customer.customername.eq(customername))
                .join(sktservice).on(customer.id.eq(sktservice.customerid))
                .fetch();
    }
}
