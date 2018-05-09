package com.diploma.jenatriplestoreservice.repositories;

import com.diploma.jenatriplestoreservice.domain.OntologyClass;
import com.diploma.jenatriplestoreservice.domain.RDFResource;
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

@SuppressWarnings("deprecation")
@Repository
public class TSRepositoryJenaImpl implements TSRepository{
    private Dataset dataset;
    private static final Logger log = LoggerFactory.getLogger( TSRepositoryJenaImpl.class );

    public TSRepositoryJenaImpl(){
        dataset = TDBFactory.createDataset("triplestore");
        log.info("Dataset was successfully created");
    }

    public void loadOntology(String path)
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
            log.info("Loading of " + path + " ontology is complete ");
            dataset.end();
        }
    }

    public List<RDFResource> listAllOntResources(){
        dataset.begin( ReadWrite.READ );
        List<RDFResource> allOntResources = new ArrayList<>();
        try {
            Model model = dataset.getDefaultModel();
            ResIterator it = model.listSubjects();
            while (it.hasNext()) {
                Resource node = it.next();
                    List<String> properties = new ArrayList<String>();
                    StmtIterator stmtIt = node.listProperties();
                    while (stmtIt.hasNext()) {
                        Statement st = stmtIt.next();
                        properties.add(st.getObject().toString());
                    }
                    RDFResource nextRDFresource = new RDFResource(node.asResource().getURI(),
                                                                  node.asResource().getNameSpace(),
                                                                  properties);
                    node.asResource().getNameSpace();
                    allOntResources.add(nextRDFresource);
            }
            dataset.commit() ;
        } finally {
        dataset.end() ;
        }
        return allOntResources;
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
            UpdateRequest request = UpdateFactory.create(sparqlQuery);
            UpdateProcessor proc = UpdateExecutionFactory.create(request, graphStore);
            proc.execute();

            dataset.commit();
        } finally {
            dataset.end();
        }
    }

    public List<String> listTransitiveProperties() {
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

    public List<OntologyClass> listOntClasses() {
        dataset.begin(ReadWrite.READ);
        List <OntologyClass> ontClasses = new ArrayList<OntologyClass>();
        try {
            Model model = dataset.getDefaultModel();
            OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, model);
            ExtendedIterator<OntClass> iterator  = ontModel.listClasses();
            while (iterator.hasNext()) {

                OntClass ontClass = iterator.next();
                ExtendedIterator<OntClass> listSubClasses= ontClass.listSubClasses();
                List <String> classInstances = new ArrayList<>();

                while (listSubClasses.hasNext()){
                    OntClass subclass = listSubClasses.next();
                    classInstances.add(subclass.getURI());
                }

                OntologyClass domainOntologyClass = new OntologyClass(ontClass.getURI(), classInstances);
                ontClasses.add(domainOntologyClass);
            }
            dataset.commit() ;
        } finally {
            dataset.end() ;
        }
        return ontClasses;
    }




}
