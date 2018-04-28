package com.diploma.jenatriplestoreservice.services;

import com.diploma.jenatriplestoreservice.repositories.TSRepository;
import org.apache.jena.ontology.TransitiveProperty;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TSServiceImp implements TSService {

    @Autowired
    private TSRepository tsRepository;

    @Override
    public void loadOntology(String URIpath) {
        tsRepository.loadModel(URIpath);
    }

    @Override
    public List<RDFNode> getAllOntObjects() {
        return  tsRepository.getAllOntObjects();
    }

    @Override
    public ResultSet execSPARQLReadQuery(String sparqlQuery) {
        return tsRepository.execSPARQLReadQuery(sparqlQuery);
    }

    @Override
    public void execSPARQLUpdateQuery(String sparqlQuery) {
        tsRepository.execSPARQLUpdateQuery(sparqlQuery);
    }

    @Override
    public List<TransitiveProperty> listTransitiveProperties() {
        return tsRepository.listTransitiveProperties();
    }
}
