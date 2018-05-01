package com.diploma.jenatriplestoreservice.services;

import com.diploma.jenatriplestoreservice.repositories.TSRepository;
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
    public List<String> getAllOntObjects() {
        return  tsRepository.getAllOntObjects();
    }

    @Override
    public String execSPARQLReadQuery(String sparqlQuery) {
        return tsRepository.execSPARQLReadQuery(sparqlQuery);
    }

    @Override
    public void execSPARQLUpdateQuery(String sparqlQuery) {
        tsRepository.execSPARQLUpdateQuery(sparqlQuery);
    }

    @Override
    public List<String> getTransitiveProperties() {
        return tsRepository.getTransitiveProperties();
    }

    public List<String> getOntClasses() {
        return tsRepository.getOntClasses();
    }

}
