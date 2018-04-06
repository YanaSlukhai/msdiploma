package com.diploma.repositories;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSetCreator {
    private Dataset dataset;
    public static final String CHEESE_DATA_FILE = "test_folder";
    @SuppressWarnings( value = "unused" )
    private static final Logger log = LoggerFactory.getLogger( DataSetCreator.class );

    public DataSetCreator(String directory) {
        Dataset dataset = TDBFactory.createDataset(directory);
        dataset.begin(ReadWrite.READ) ;
        // Get model inside the transaction
        Model model = dataset.getDefaultModel() ;
        dataset.end() ;
    }

    public void loadModel( String modelName, String path )
    {
        Model model = null;

        dataset.begin( ReadWrite.WRITE );
        try
        {
            model = dataset.getNamedModel( modelName );
            FileManager.get().readModel(model, path);
            dataset.commit();

        }
        finally
        {
            dataset.end();
        }
    }
}
