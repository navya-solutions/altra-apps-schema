package com.altra.apps.schema.rdbms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "Tag")
/**
 * Possible values are:
 * DEPARTMENT_HEAD,PRIVATE_TUTOR,SENIOR_MANAGEMENT,STUDENT,TEACHER
 */
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_interests_id")
    @JsonIgnore
    private Tag topicInterests;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_interests_id")
    @JsonIgnore
    private Tag levelInterests;

}
