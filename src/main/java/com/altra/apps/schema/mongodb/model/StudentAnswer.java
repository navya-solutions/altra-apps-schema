package com.altra.apps.schema.mongodb.model;

import com.altra.apps.schema.mongodb.type.AnswerOption;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Set;

@Document(collection = "student_answers")
@Getter
@Setter
@ToString
@Builder
public class StudentAnswer implements Serializable {
    @Id
    private String id;
    private int revision;
    private Long answerDate;
    private String comment;
    @DBRef(lazy = true)
    private Exam exam;
    @DBRef(lazy = true)
    private Question question;
    @DBRef(lazy = true)
    private Student student;
    private Set<AnswerOption> answerOptions;
    private String answerText;


}
