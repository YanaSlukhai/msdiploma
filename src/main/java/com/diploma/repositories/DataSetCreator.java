package com.diploma.repositories;

import org.apache.jena.ontology.*;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.*;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataSetCreator {
    private Dataset dataset;
    private static final Logger log = LoggerFactory.getLogger( DataSetCreator.class );
    OntModel model = null;

    public DataSetCreator(){
        dataset = TDBFactory.createDataset("test_dir");
        model = null;
    }

    public void loadModel(  String path )
    {
        dataset.begin( ReadWrite.WRITE );
        try
        {
            model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
            FileManager.get().readModel(model, path);
            dataset.commit();
        }
        finally
        {
            //dataset.commit();
            dataset.end();
        }
    }

    public List<String> getAllOntProperties(OntModel model ){
        List<String> allOntProperties = new ArrayList<String>();
        ExtendedIterator<OntProperty> it=model.listAllOntProperties();
        while(it.hasNext()){
            OntProperty property=(OntProperty)it.next();
            allOntProperties.add(property.getURI());
            System.out.println("Property : "+property.getURI());
        }
        return allOntProperties;
    }
}
