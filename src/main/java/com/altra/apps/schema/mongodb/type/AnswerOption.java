package com.altra.apps.schema.mongodb.type;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnswerOption implements Serializable {
    private String id;
    private String description;

}
