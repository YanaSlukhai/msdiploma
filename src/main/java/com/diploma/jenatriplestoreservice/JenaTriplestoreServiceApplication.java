package com.diploma.jenatriplestoreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.diploma")
public class JenaTriplestoreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JenaTriplestoreServiceApplication.class, args);
    }
}
