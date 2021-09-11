package com.altra.apps.schema.graphql.resolver.query;

import com.altra.apps.schema.graphql.type.CurriculumHierarchyType;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@AllArgsConstructor
@Component
public class HelloQueryResolver implements GraphQLQueryResolver {

    public String getHello(DataFetchingEnvironment environment) {
        return "HELLO!!";
    }

    /*public List<CurriculumHierarchyType> getAllCurriculumHierarchy() {
        return List.of(new CurriculumHierarchyType());

    }*/


}
