package com.altra.apps.schema;

import com.altra.apps.schema.test.data.ProjectTestDataLoader;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AltraAppsSchemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AltraAppsSchemaApplication.class, args);
    }

    @Bean
    ApplicationRunner ApplicationRunner(ProjectTestDataLoader projectTestDataLoader) {
        return args -> {
            // setup test data
            projectTestDataLoader.process();
            ;
        };
    }
}
