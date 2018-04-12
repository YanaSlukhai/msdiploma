package com.diploma.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataSetCreator.class)
public class DataSetCreatorTests {

    @Autowired
    DataSetCreator dataSetCreator;

    @Test
    public void loadModelTest() {
        dataSetCreator.loadModel("https://bitbucket.org/uamsdbmi/dron/raw/master/dron-upper.owl");
    }

}
