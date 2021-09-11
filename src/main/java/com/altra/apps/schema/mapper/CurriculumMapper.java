package com.altra.apps.schema.mapper;


import com.altra.apps.schema.graphql.type.CurriculumHierarchyType;
import com.altra.apps.schema.rdbms.model.Curriculum;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CurriculumMapper {

    // Mappings from GraphQL Type Objects ---> JPA Objects
    Curriculum mapGraphQLTypeToGrpcApi(CurriculumHierarchyType source);

    // Mappings from JPA Objects ---> GraphQL Type Objects

    CurriculumHierarchyType mapGrpcApiToGraphQLType(Curriculum source);


}
