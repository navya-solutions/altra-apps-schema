package com.altra.apps.schema.mongodb.model;

import com.altra.apps.schema.mongodb.type.AnswerOption;
import com.altra.apps.schema.mongodb.type.QuestionOption;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Set;

@Document(collection = "question")
@Getter
@Setter
@ToString
@Builder
public class Question implements Serializable {
    @Id
    @JsonIgnore
    private String id;
    private int revision;
    private String questionText;
    private String hint;
    private String imageUrl;
    private int mark;
    private String questionCode;
    private String subjectCode;
    private Set<QuestionOption> questionOptions;
    private Set<AnswerOption> answerOptions;
    private String answerText;

}
