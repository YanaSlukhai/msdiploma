package com.diploma.jenatriplestoreservice.services;

import org.apache.jena.ontology.TransitiveProperty;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;

import java.util.List;

public interface TSService {
     void loadOntology(String URIpath);
     List<RDFNode> getAllOntObjects();
     ResultSet execSPARQLReadQuery(String sparqlQuery);
     void execSPARQLUpdateQuery(String sparqlQuery);
     public List<TransitiveProperty> listTransitiveProperties();
}
