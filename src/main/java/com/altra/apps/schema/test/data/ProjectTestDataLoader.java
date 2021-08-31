package com.altra.apps.schema.test.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ProjectTestDataLoader {

    @Autowired
    private Set<TestDataLoader> testDataLoaders;

    public void process() {
        testDataLoaders.stream().forEach(tl -> tl.testDataLoader());

    }


}
