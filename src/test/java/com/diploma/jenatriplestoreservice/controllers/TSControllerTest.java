package com.diploma.jenatriplestoreservice.controllers;


import com.diploma.jenatriplestoreservice.JenaTriplestoreServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JenaTriplestoreServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TSControllerTest {

    @Autowired
    TSController ts;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllThePropsTest() {
        System.out.println(this.restTemplate.getForObject("http://localhost:" + port + "/getdata/get-all-properties", String.class));
    }

}
