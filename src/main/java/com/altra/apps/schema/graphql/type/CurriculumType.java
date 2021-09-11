package com.altra.apps.schema.graphql.type;

import graphql.annotations.annotationTypes.GraphQLType;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@GraphQLType
public class CurriculumType implements Serializable {
    private String pid;
    private boolean hasPublicAccess;
    private String title, shortTitle, description;
    private Set<TopicLabelType> topicLabels = new HashSet<>();
    private Set<TopicType> topics = new HashSet<>();

}
