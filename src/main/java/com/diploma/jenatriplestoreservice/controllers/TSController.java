package com.diploma.jenatriplestoreservice.controllers;

import com.diploma.jenatriplestoreservice.repositories.TSRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TSController {
    @Autowired
    TSRepository dataSetCreator;

    @GetMapping(value = "/getdata/get-all-properties")
    @ResponseBody
    public String getAllProperties() {
        List<RDFNode> allOntProperties = dataSetCreator.getAllOntProperties();
        System.out.println(allOntProperties.size());
        List<String> props = new ArrayList<String>();
        for(RDFNode prop : allOntProperties){
            props.add(prop.toString());
                System.out.println("IT IS PROP" + prop.toString());
        }
        final StringWriter sw =new StringWriter();
        final ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(sw, props);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }
}
