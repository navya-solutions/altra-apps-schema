package com.altra.apps.schema.rdbms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TOPIC_LABEL")

public class TopicLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String title;
    //Used to define the topic label order in the curriculum i.e. Subject ->1, Section ->2 etc..
    @Column(nullable = false)
    private Integer orderId;

    @OneToMany(mappedBy = "topicLabel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Topic> topics = new LinkedList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id", foreignKey = @ForeignKey(name = "curriculum_topic_label"))
    @JsonIgnore
    private Curriculum curriculum;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TopicLabel)) return false;
        return id != null && id.equals(((TopicLabel) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
