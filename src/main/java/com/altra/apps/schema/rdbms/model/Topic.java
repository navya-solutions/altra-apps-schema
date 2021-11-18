package com.altra.apps.schema.rdbms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "TOPIC")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String title, topicUnitTitle;
    //private String label;
    private boolean hasChildren,active;

    private String description;

    /* This field is used to manage the different type of relationships between topics, for example sameAs, similarTo etc.
    This mapping can be done across the curriculums*/
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<IdenticalTopic> identicalTopics = new HashSet<>();

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    //@JsonIgnore
    private List<Topic> children = new LinkedList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "parent_topic"))
    @JsonIgnore
    private Topic parent;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<Block> blocks = new LinkedList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id", foreignKey = @ForeignKey(name = "curriculum_topic"))
    @JsonIgnore
    private Curriculum curriculum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topicLabel_id", foreignKey = @ForeignKey(name = "topic_topicLabel"))
    @JsonIgnore
    private TopicLabel topicLabel;

    public void addChildren(Topic topic) {
        if (topic.getTopicLabel().getOrderId() == 1) {
            topic.setTopicUnitTitle(topic.getTitle());
        } else {
            topic.setTopicUnitTitle(String.format("%s#%s", topic.topicUnitTitle == null ? topic.title : topic.topicUnitTitle, this.getTitle()));
            topic.setTopicUnitTitle(String.format("%s#%s", topic.getTopicUnitTitle(), topic.getCurriculum().getShortTitle()));
        }

        this.children.add(topic);
        topic.setParent(this);
    }

    public void addBlock(Block block) {
        this.blocks.add(block);
        block.setTopic(this);
    }


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
