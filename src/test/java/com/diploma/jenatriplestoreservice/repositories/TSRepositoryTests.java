package com.diploma.jenatriplestoreservice.repositories;

import org.apache.jena.query.ResultSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = TSRepositoryJenaImpl.class)
public class TSRepositoryTests {

    @Autowired
    TSRepositoryJenaImpl tsRepositoryJenaImpl;

    @Test
    public void execSPARQLReadQueryTest() {
       // tsRepository.loadOntModel("https://bitbucket.org/uamsdbmi/dron/raw/master/dron-upper.owl", "testDir1" );
        String rs = tsRepositoryJenaImpl.execSPARQLReadQuery("SELECT * {?s ?p ?o} LIMIT 10");
    }

}
