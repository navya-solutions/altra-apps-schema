package com.altra.apps.schema.rdbms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "LEVEL")
/**
 * A level is a way of differentiating a topic's difficulty
 * Examples: MATHS, HISTORY etc
 */
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String pid;
    private String title, description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topicLabel_id")
    @JsonIgnore
    private TopicLabel topicLabel;

    //TODO: need more details how this field will be used??
    private String sameAs, similarTo;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Unit unit;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Level)) return false;
        return id != null && id.equals(((Level) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
