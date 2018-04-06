package com.diploma.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSetCreatorTests {

    @Autowired
    @Qualifier("test_folder")
    DataSetCreator datasetCreator;

    @Test
    public void constructorTest() {
        final String CHEESE_DATA_FILE = "test_folder";

    }

}
