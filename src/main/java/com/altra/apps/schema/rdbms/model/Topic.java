package com.altra.apps.schema.rdbms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity

@Table(name = "TOPIC")
/**
 * A topic is a collection of concepts that are taught
 * Examples: MATHS, HISTORY etc
 */
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String pid;
    private String title, description;

    //TODO: need more details how this field will be used??
    private String sameAs, similarTo;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topicLabel_id")
    @JsonIgnore
    private TopicLabel topicLabel;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Topic)) return false;
        return id != null && id.equals(((Topic) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
