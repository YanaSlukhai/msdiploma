package com.diploma.jenatriplestoreservice.domain;

import java.util.List;

public class RDFResource {
    private String URI;
    private List<String> properties;
    private String nameSpace;

    public RDFResource(String URI, String nameSpace, List <String> properties){
        this.URI = URI;
        this.nameSpace = nameSpace;
        this.properties = properties;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String localName) {
        this.URI = localName;
    }

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }
}
