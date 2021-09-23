package com.altra.apps.schema.graphql.type;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Data

public class CurriculumHierarchyType implements Serializable {
    private List<CurriculumType> curriculumTypeList = new LinkedList<>();
}
