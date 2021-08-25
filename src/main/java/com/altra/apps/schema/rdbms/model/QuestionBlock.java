package com.altra.apps.schema.rdbms.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "QUESTION_BLOCK")
public class QuestionBlock extends Block {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(nullable = false, unique = true)
    private String pid;*/
    private String title;
    private String generalGuidance, additonalGuidance;
    //TODO:: do it later
    //private Block context;
    //private Block markingScheme;
    private Integer marks;
    //TODO:: Convert it to list  later
    private String externalLink;
    //TODO:: Convert it to list  later
    private String exampleSolutions;


}
