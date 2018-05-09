package com.diploma.jenatriplestoreservice.services;

import com.diploma.jenatriplestoreservice.domain.OntologyClass;
import com.diploma.jenatriplestoreservice.domain.RDFResource;
import com.diploma.jenatriplestoreservice.repositories.TSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TSServiceImp implements TSService {

    private final TSRepository tsRepository;

    @Autowired
    public TSServiceImp(TSRepository tsRepository) {
        this.tsRepository = tsRepository;
    }

    @Override
    public void loadOntology(String URIpath) {
        tsRepository.loadOntology(URIpath);
    }

    @Override
    public List<RDFResource> listAllOntSubjects() {
        return  tsRepository.listAllOntResources();
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
    public List<String> listTransitiveProperties() {
        return tsRepository.listTransitiveProperties();
    }

    public List<OntologyClass> listOntClasses() {
        return tsRepository.listOntClasses();
    }

}
