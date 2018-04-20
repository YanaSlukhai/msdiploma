package com.diploma.jenatriplestoreservice.repositories;

import org.apache.jena.ontology.*;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.*;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TSRepository {
    private static final Logger log = LoggerFactory.getLogger( TSRepository.class );
    private OntModel ontModel;
    private Dataset ds;

    public TSRepository(String TDBdir){
        ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
        ds = TDBFactory.createDataset(TDBdir);
    }

    public void loadOntModel(String ontPath, String datasetTargetDir)
    {
        Dataset dataset  = TDBFactory.createDataset(datasetTargetDir);
        dataset.begin( ReadWrite.WRITE );
        FileManager.get().readModel(ontModel, ontPath);
        dataset.commit();
    }

    public List<OntProperty> getAllOntProperties(){
        List<OntProperty> allOntProperties = new ArrayList<OntProperty>();
        ExtendedIterator<OntProperty> it=this.ontModel.listAllOntProperties();
        while(it.hasNext()){
            OntProperty property = it.next();
            allOntProperties.add(property);
        }
        return allOntProperties;
    }

    public List<OntClass> getAllOntClasses(){
        List<OntClass> allClasses= new ArrayList<OntClass>();
        ExtendedIterator<OntClass> it=this.ontModel.listClasses();
        while(it.hasNext()){
            OntClass ont_class=it.next();
            allClasses.add(ont_class);
        }
        return allClasses;
    }


}
