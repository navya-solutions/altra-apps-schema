package com.altra.apps.schema.rdbms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "CURRICULUM")
/**
 * A curriculum is a collection of topics with an owner
 *database
 */

public class Curriculum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    //anyone can see this curriculum.
    private boolean hasPubliclyAccessible;
    @Column(nullable = false)
    private String name, shortTitle;

    @Lob
    private String description;

    /*    @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private CurriculumTopicLabelsEnum curriculumTopicLabels;
      */

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TopicLabel> topicLabels = new HashSet<>();


    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CurriculumChangeRequest> curriculumChangeRequests = new HashSet<>();

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<Topic> topics = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "curriculum_institution"))
    @JsonIgnore
    private Institution institution;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "curriculum_country"))
    @JsonIgnore
    private Country country;

    //Contain the source Curriculum reference pid from newly created Curriculum is copied
    private String refCurriculumId;


    public void addTopicLabel(TopicLabel topicLabel) {
        this.topicLabels.add(topicLabel);
        topicLabel.setCurriculum(this);
    }

    public void addCurriculumSuggestions(CurriculumChangeRequest suggestions) {
        this.curriculumChangeRequests.add(suggestions);
        suggestions.setCurriculum(this);
    }

    public void addTopic(Topic topic) {
        this.topics.add(topic);
        topic.setCurriculum(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Curriculum)) return false;
        return id != null && id.equals(((Curriculum) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
