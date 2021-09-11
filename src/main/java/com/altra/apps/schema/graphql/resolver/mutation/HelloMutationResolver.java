package com.altra.apps.schema.graphql.resolver.mutation;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class HelloMutationResolver implements GraphQLMutationResolver {

    public String hello(String message, DataFetchingEnvironment environment) {
        return String.format("Hello %s", message);
    }


}
