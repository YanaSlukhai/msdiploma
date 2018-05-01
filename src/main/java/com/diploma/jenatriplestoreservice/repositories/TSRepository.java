package com.diploma.jenatriplestoreservice.repositories;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.TransitiveProperty;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;

import java.util.List;

public interface TSRepository {
    void loadModel(String path);

     List<String> getAllOntObjects();

     String execSPARQLReadQuery(String sparqlQuery) ;

     void execSPARQLUpdateQuery(String sparqlQuery) ;

     public List<String> getTransitiveProperties() ;

     public List<String>  getOntClasses() ;
}
