package com.altra.apps.schema.graphql.type;

import lombok.Data;

import java.io.Serializable;

@Data

public class TopicLabelType implements Serializable {
    private String pid;
    private String title;
    // sequence of topic labels i.e. Subject ->1, Section ->2 etc..
    private Integer sequence;

}
