package com.diploma.jenatriplestoreservice.repositories;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.TransitiveProperty;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.update.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TSRepositoryJenaImpl implements TSRepository{
    private static final Logger log = LoggerFactory.getLogger( TSRepositoryJenaImpl.class );

    private Dataset dataset;

    public TSRepositoryJenaImpl(){
        dataset = TDBFactory.createDataset("triplestore");
        System.out.println("Loading model");
    }

    public void loadModel(String path)
    {
        Model model = null;

        dataset.begin( ReadWrite.WRITE );
        try
        {
            model = dataset.getDefaultModel();
            FileManager.get().readModel( model, path );
            dataset.commit();
        }
        finally
        {
            log.info("Loading of ontology is complete " +  path);
            dataset.end();
        }
    }

    public List<String> getAllOntObjects(){
        dataset.begin( ReadWrite.READ );
        List<String> allOntProperties = new ArrayList<String>();
        try {
            Model Model = dataset.getDefaultModel();
            NodeIterator it = Model.listObjects();
            while (it.hasNext()) {
                RDFNode node = it.next();
                allOntProperties.add(node.toString());
            }
            dataset.commit() ;
        } finally {
        dataset.end() ;
        }
        return allOntProperties;
    }

    public String execSPARQLReadQuery(String sparqlQuery) {
        dataset.begin(ReadWrite.READ);
        QueryExecution qExec = QueryExecutionFactory.create(sparqlQuery, dataset);
        ResultSet rs = qExec.execSelect();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(outputStream, rs);
        String jsonRS = new String(outputStream.toByteArray());
        dataset.commit();
        return jsonRS;
    }

    public void execSPARQLUpdateQuery(String sparqlQuery) {
        dataset.begin(ReadWrite.WRITE);
        try {

            GraphStore graphStore = GraphStoreFactory.create(dataset);

           // String sparqlUpdateString = StrUtils.strjoinNL(
             //       "PREFIX : <http://example/>",
               //     "INSERT { :s :p ?now } WHERE { BIND(now() AS ?now) }"
            //) ;
            UpdateRequest request = UpdateFactory.create(sparqlQuery);
            UpdateProcessor proc = UpdateExecutionFactory.create(request, graphStore);
            proc.execute();

            dataset.commit();
        } finally {
            dataset.end();
        }
    }

    public List<String> getTransitiveProperties() {
        dataset.begin(ReadWrite.READ);
        List transitiveProps = new ArrayList<String>();
        try {
            Model model = dataset.getDefaultModel();
            OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, model);
            ExtendedIterator<TransitiveProperty> iterator  = ontModel.listTransitiveProperties();

            while (iterator.hasNext()) {
                TransitiveProperty transProp = iterator.next();
                transitiveProps.add(transProp.getURI());
            }
            dataset.commit() ;
        } finally {
            dataset.end() ;
        }
        return transitiveProps;
    }

    public List<String> getOntClasses() {
        dataset.begin(ReadWrite.READ);
        List ontClasses = new ArrayList<String>();
        try {
            Model model = dataset.getDefaultModel();
            OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, model);
            ExtendedIterator<OntClass> iterator  = ontModel.listClasses();
            while (iterator.hasNext()) {
                OntClass ontClass = iterator.next();
                ontClasses.add(ontClass.toString());
            }
            dataset.commit() ;
        } finally {
            dataset.end() ;
        }
        return ontClasses;
    }




}
