package com.altra.apps.schema.graphql.type;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data

public class CurriculumType implements Serializable {
    private String pid;
    private boolean hasPublicAccess;
    private String title, shortTitle, description;
    private Set<TopicLabelType> topicLabels = new HashSet<>();
    private Set<TopicType> topics = new HashSet<>();

}
