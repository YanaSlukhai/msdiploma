package com.diploma.jenatriplestoreservice.repositories;

import org.apache.jena.ontology.*;
import org.apache.jena.query.*;
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
    //private OntModel ontModel;
    private Dataset ds;

    public TSRepository(){
        //ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
        ds = TDBFactory.createDataset("testDirLOAD");
        loadModel("model1", "https://bitbucket.org/uamsdbmi/dron/raw/master/dron-upper.owl");
        System.out.println("Loading model");
    }

    public void loadModel( String modelName, String path )
    {
        Model model = null;

        ds.begin( ReadWrite.WRITE );
        try
        {
            model = ds.getDefaultModel();
            FileManager.get().readModel( model, path );
            ds.commit();
        }
        finally
        {
            ds.end();
        }
    }

    public List<RDFNode> getAllOntProperties(){
        ds.begin( ReadWrite.READ );
        List<RDFNode> allOntProperties = new ArrayList<RDFNode>();
        try {
            Model ontModel = ds.getDefaultModel();
            NodeIterator it = ontModel.listObjects();
            while (it.hasNext()) {
                RDFNode node = it.next();
                allOntProperties.add(node);
            }
            ds.commit() ;
        } finally {
        ds.end() ;
        }
        return allOntProperties;
    }
    public ResultSet execSPARQLreadQuery(String sparqlQuery) {
        ds.begin(ReadWrite.READ);

        QueryExecution qExec = QueryExecutionFactory.create(sparqlQuery, ds);
        ResultSet rs = qExec.execSelect();
        System.out.println(ResultSetFormatter.asText(rs));
        return rs;
    }


    public List<OntClass> getAllOntClasses(){
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
        List<OntClass> allClasses= new ArrayList<OntClass>();
        ExtendedIterator<OntClass> it=ontModel.listClasses();
        while(it.hasNext()){
            OntClass ont_class=it.next();
            allClasses.add(ont_class);
        }
        return allClasses;
    }


}
