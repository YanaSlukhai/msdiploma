package com.diploma.jenatriplestoreservice.repositories;

import com.diploma.jenatriplestoreservice.domain.OntologyClass;
import com.diploma.jenatriplestoreservice.domain.RDFResource;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TSRepository {
     void loadOntology(String path);

     List<RDFResource> listAllOntResources();

     String execSPARQLReadQuery(String sparqlQuery) ;

     void execSPARQLUpdateQuery(String sparqlQuery) ;

     List<String> listTransitiveProperties() ;

     List<OntologyClass> listOntClasses() ;
}
