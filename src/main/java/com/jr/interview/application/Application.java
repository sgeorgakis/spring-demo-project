package com.jr.interview.application;

import com.jr.interview.infrastructure.rest.Controller;
import com.jr.interview.persistence.HibernateUtil;
import com.jr.interview.persistence.repositories.Test2Repository;
import com.jr.interview.persistence.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by s.georgakis on 28/2/2017.
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    @Autowired
    private HibernateUtil hibernateUtil;

    @Bean
    public HibernateUtil hibernateUtil() {
        return new HibernateUtil();
    }
    @Bean
    public Test2Repository test2Repository() {
        return new Test2Repository();
    }

    @Bean
    public TestRepository testRepository() {
        return new TestRepository();
    }


    @Bean
    public Controller controller() {
        return new Controller();
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}