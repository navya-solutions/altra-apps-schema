package com.altra.apps.schema.graphql.type;

import com.altra.apps.schema.rdbms.model.TopicLabel;
import lombok.Data;

import java.io.Serializable;

@Data

public class TopicType implements Serializable {
    private String pid;
    private String title, description;
    private String label;
    boolean hasChildren;
    private String topicUnitTitle;
    private TopicLabel topicLabel;
}
