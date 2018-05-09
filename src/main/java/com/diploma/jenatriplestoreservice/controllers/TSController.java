package com.diploma.jenatriplestoreservice.controllers;

import com.diploma.jenatriplestoreservice.domain.OntologyClass;
import com.diploma.jenatriplestoreservice.domain.RDFResource;
import com.diploma.jenatriplestoreservice.dto.OntologyURIDTO;
import com.diploma.jenatriplestoreservice.dto.SPARQLQueryDTO;
import com.diploma.jenatriplestoreservice.services.TSServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;
import org.apache.jena.rdf.model.RDFNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Controller
public class TSController {

    private final TSServiceImp tsService;

    private final Logger logger = LoggerFactory.getLogger(TSController.class);

    @Autowired
    public TSController(TSServiceImp tsService) {
        this.tsService = tsService;
    }

    @GetMapping(value = "/getdata/get-all-subjects")
    @ResponseBody
    public List<RDFResource>  listAllSubjects() {
        return tsService.listAllOntSubjects();

    }
    @GetMapping(value = "/getdata/get-transitive-properties")
    @ResponseBody
    public List<String> listTransitiveProperties() {
        return tsService.listTransitiveProperties();

    }

    @GetMapping(value = "/getdata/get-all-ont-classes")
    @ResponseBody
    public List<OntologyClass> listOntClasses() {
        return tsService.listOntClasses();
    }

    @RequestMapping(method = RequestMethod.POST,
                    value = "getdata/read-sparql")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String execSPARQLReadQuery (@RequestBody SPARQLQueryDTO sparqlDTO) {
            System.out.println(sparqlDTO.getSparqlQuery());
            String jsonRS  = tsService.execSPARQLReadQuery(sparqlDTO.getSparqlQuery());
            System.out.println(jsonRS);
            return jsonRS;
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "postdata/load-ontology")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void loadOntologyToTS (@RequestBody OntologyURIDTO ontologyURIDTO) {
            System.out.println(ontologyURIDTO.getontologyURI());
            tsService.loadOntology(ontologyURIDTO.getontologyURI());
            logger.info(ontologyURIDTO.getontologyURI());
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "postdata/update-sparql")
    @ResponseStatus(HttpStatus.OK)
    public void  execSPARQLUpdateQuery (@RequestBody SPARQLQueryDTO sparqlDTO) {
            tsService.execSPARQLUpdateQuery(sparqlDTO.getSparqlQuery());
    }



}
