package com.mooo.bitarus.chucknorris;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
class QueryDslConfiguration {

    @PersistenceContext
    private EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}

