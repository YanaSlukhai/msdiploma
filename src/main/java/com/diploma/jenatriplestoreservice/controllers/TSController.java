package com.diploma.jenatriplestoreservice.controllers;

import com.diploma.jenatriplestoreservice.dto.OntologyURIDTO;
import com.diploma.jenatriplestoreservice.dto.SPARQLQueryDTO;
import com.diploma.jenatriplestoreservice.services.TSServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.jena.ontology.TransitiveProperty;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.RDFNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TSController {

    @Autowired
    private TSServiceImp tsService;

    private final Logger logger = LoggerFactory.getLogger(TSController.class);

    @GetMapping(value = "/getdata/get-all-properties")
    @ResponseBody
    public String getAllObjects() {
        List<String> allOntProperties = tsService.getAllOntObjects();
        System.out.println(allOntProperties.size());
        final StringWriter sw =new StringWriter();
        final ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(sw, allOntProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }
    @GetMapping(value = "/getdata/get-transitive-properties")
    @ResponseBody
    public String getTransitiveProperties() {
        List<String> allTransitiveProperties = tsService.getTransitiveProperties();
        System.out.println(allTransitiveProperties.size());
        final StringWriter sw =new StringWriter();
        final ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(sw, allTransitiveProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    @GetMapping(value = "/getdata/get-all-ont-classes")
    @ResponseBody
    public String getOntClasses() {
        List<String> allTransitiveProperties = tsService.getOntClasses();
        System.out.println(allTransitiveProperties.size());
        final StringWriter sw =new StringWriter();
        final ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(sw, allTransitiveProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    @RequestMapping(method = RequestMethod.POST,
                    value = "getdata/read-sparql")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String execSPARQLReadQuery (@RequestBody String jsonSPARQL) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            SPARQLQueryDTO sparqlDTO = mapper.readValue(jsonSPARQL, SPARQLQueryDTO.class);
            System.out.println(sparqlDTO.getSparqlQuery());

            String jsonRS  = tsService.execSPARQLReadQuery(sparqlDTO.getSparqlQuery());
            System.out.println(jsonRS);
            return jsonRS;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "postdata/load-ontology")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String loadOntologyToTS (@RequestBody String jsonURI) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            OntologyURIDTO ontologyURIDTO = mapper.readValue(jsonURI, OntologyURIDTO.class);
            System.out.println(ontologyURIDTO.getontologyURI());
            tsService.loadOntology(ontologyURIDTO.getontologyURI());
            logger.info(ontologyURIDTO.getontologyURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "postdata/update-sparql")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String  execSPARQLUpdateQuery (@RequestBody String jsonSPARQL) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            SPARQLQueryDTO sparqlDTO = mapper.readValue(jsonSPARQL, SPARQLQueryDTO.class);
            System.out.println(sparqlDTO.getSparqlQuery());
            tsService.execSPARQLUpdateQuery(sparqlDTO.getSparqlQuery());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
