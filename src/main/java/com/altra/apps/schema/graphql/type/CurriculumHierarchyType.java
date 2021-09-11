package com.altra.apps.schema.graphql.type;

import graphql.annotations.annotationTypes.GraphQLType;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Data
@GraphQLType
public class CurriculumHierarchyType implements Serializable {
    private List<CurriculumType> curriculumTypeList = new LinkedList<>();
}
