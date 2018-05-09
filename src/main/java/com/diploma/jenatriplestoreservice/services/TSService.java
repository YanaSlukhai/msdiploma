package com.diploma.jenatriplestoreservice.services;

import com.diploma.jenatriplestoreservice.domain.OntologyClass;
import com.diploma.jenatriplestoreservice.domain.RDFResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TSService {
     void loadOntology(String URIpath);
     List<RDFResource> listAllOntSubjects();
     String execSPARQLReadQuery(String sparqlQuery);
     void execSPARQLUpdateQuery(String sparqlQuery);
      List<String> listTransitiveProperties();
      List<OntologyClass> listOntClasses() ;
}