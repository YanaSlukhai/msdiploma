package com.diploma.jenatriplestoreservice.repositories;

import com.diploma.jenatriplestoreservice.domain.RDFResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TSRepositoryJenaImpl.class)
public class TSRepositoryTests {

   /* @Autowired
    TSRepositoryJenaImpl tsRepositoryJenaImpl;

    @Test
    public void basicTest() {
        tsRepositoryJenaImpl.loadOntology("https://bitbucket.org/uamsdbmi/dron/raw/master/dron-upper.owl");
        List<RDFResource> rs = tsRepositoryJenaImpl.listAllOntResources();
        rs.forEach(rdfResource -> System.out.println("LOC = "+ rdfResource.getURI()+
                " NS = "+ rdfResource.getNameSpace() + " PROPS " + rdfResource.getProperties().toString()));
    }
*/
}
