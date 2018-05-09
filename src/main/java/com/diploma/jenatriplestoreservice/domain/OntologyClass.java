package com.diploma.jenatriplestoreservice.domain;

import java.util.List;

public class OntologyClass {
    private String URI;
    private List<String> subclasses;

    public OntologyClass(String URI, List<String> subclasses){
        this.URI = URI;
        this.subclasses = subclasses;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public List<String> getSubclasses() {
        return subclasses;
    }

    public void setSubclasses(List<String> subclasses) {
        this.subclasses = subclasses;
    }
}
