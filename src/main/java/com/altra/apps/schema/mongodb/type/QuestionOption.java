package com.altra.apps.schema.mongodb.type;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionOption implements Serializable {
    private String id;
    private String description;

}
