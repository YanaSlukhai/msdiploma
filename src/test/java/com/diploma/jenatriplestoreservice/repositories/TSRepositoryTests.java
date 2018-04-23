package com.diploma.jenatriplestoreservice.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TSRepository.class)
public class TSRepositoryTests {

    @Autowired
    TSRepository tsRepository;

    @Test
    public void loadModelTest() {
       // tsRepository.loadOntModel("https://bitbucket.org/uamsdbmi/dron/raw/master/dron-upper.owl", "testDir1" );
    }

}
