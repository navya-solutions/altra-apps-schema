package com.altra.apps.schema.mongodb.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "exam")
@Getter
@Setter
@ToString
@Builder
public class Exam implements Serializable {
    @Id
    private String id;
    private int revision;
    private String name;
    private long createdAt;
    private String description;
    private String otherDetails;
    private int markPerQuestion;//if set, override the question mark field
    private String subjectCode;
    @DBRef(lazy = true)
    private Set<Question> questions = new HashSet<>();

}
