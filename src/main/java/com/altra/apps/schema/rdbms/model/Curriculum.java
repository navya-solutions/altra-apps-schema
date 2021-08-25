package com.altra.apps.schema.rdbms.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String pid;
    private String title, shortTitle, description;

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CurriculumChangeRequest> curriculumSuggestions = new HashSet<>();

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Country> countries = new HashSet<>();

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TopicLabel> topicLabels = new HashSet<>();

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<Unit> units = new HashSet<>();

    @OneToOne(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Institution owner;

    private String refCurriculumPid;

    public void setOwner(Institution owner) {
        if (owner == null) {
            if (this.owner != null) {
                this.owner.setCurriculum(null);
            }
        } else {
            owner.setCurriculum(this);
        }
        this.owner = owner;
    }

    public void addCurriculumSuggestions(CurriculumChangeRequest suggestions) {
        this.curriculumSuggestions.add(suggestions);
        suggestions.setCurriculum(this);
    }

    public void addTopicLabel(TopicLabel topicLabel) {
        this.topicLabels.add(topicLabel);
        topicLabel.setCurriculum(this);
    }


    public void addUnit(Unit unit) {
        this.units.add(unit);
        unit.setCurriculum(this);
    }

    public void addCountry(Country country) {
        this.countries.add(country);
        country.setCurriculum(this);
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
