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
@Table(name = "Unit")
/**
 * A unit is a topic with a level and its own subtopics
 */
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String pid;
    private String title, description;

    @OneToMany(mappedBy = "parentUnit", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Unit> childUnits = new LinkedList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Unit parentUnit;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<Block> blocks = new LinkedList<>();

    @OneToOne(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Level level;

    @OneToOne(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Topic topic;

    //TODO: cyclic dependency?? do we need that??
    /*@OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Topic> topics = new HashSet<>();*/
    //TODO: need more details how this field will be used??
    private String sameAs, similarTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id")
    @JsonIgnore
    private Curriculum curriculum;

    public void setLevel(Level level) {
        if (level == null) {
            if (this.level != null) {
                this.level.setUnit(null);
            }
        } else {
            level.setUnit(this);

        }
        this.level = level;
    }

    public void setTopic(Topic topic) {
        if (topic == null) {
            if (this.topic != null) {
                this.topic.setUnit(null);
            }
        } else {
            topic.setUnit(this);
            ;
        }
        this.topic = topic;
    }

    public void addChildUnit(Unit unit) {
        this.childUnits.add(unit);
        unit.setParentUnit(this);
    }

    public void addBlock(Block block) {
        this.blocks.add(block);
        block.setUnit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Unit)) return false;
        return id != null && id.equals(((Unit) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
