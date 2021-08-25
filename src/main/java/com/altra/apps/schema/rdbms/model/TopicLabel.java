package com.altra.apps.schema.rdbms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "TOPIC_LABEL")
public class TopicLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    // sequence of topic labels i.e. Subject ->1, Section ->2 etc..
    private Integer sequence;
    @Column(nullable = false, unique = true)
    private String pid;

    @OneToMany(mappedBy = "topicLabel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Level> levels = new HashSet<>();

    @OneToMany(mappedBy = "topicLabel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Topic> topics = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id")
    @JsonIgnore
    private Curriculum curriculum;

    public void addLevel(Level level) {
        this.levels.add(level);
        level.setTopicLabel(this);
    }

    public void addTopic(Topic topic) {
        this.topics.add(topic);
        topic.setTopicLabel(this);
    }

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
