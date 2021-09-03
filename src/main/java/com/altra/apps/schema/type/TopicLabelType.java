package com.altra.apps.schema.type;

import lombok.Data;

import java.io.Serializable;

@Data
public class TopicLabelType implements Serializable {
    private String title;
    // sequence of topic labels i.e. Subject ->1, Section ->2 etc..
    private Integer sequence;
}
