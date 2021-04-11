package com.challenge.twitterapi.conf;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class to be able to inject the mappers in the services and to be able to convert the dto classes to model classes
 * and vice versa
 */
@Configuration
public class DozerMapper {


    /**
     * Create instance of DozerBeanMapper to inject
     * @return Mapper
     */
    @Bean
    public Mapper beanMapper() {
        return new DozerBeanMapper();
    }
}