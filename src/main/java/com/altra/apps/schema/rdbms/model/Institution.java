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
@Table(name = "INSTITUTION")
/**
 * A unit is a topic with a level and its own subtopics
 */
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String pid;
    private String name, refId, description;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Curriculum curriculum;

}
