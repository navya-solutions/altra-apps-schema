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
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "block_id", foreignKey = @ForeignKey(name = "user_block_tag"))
    private Block block;

    @OneToOne
    @JoinColumn(name = "topic_id", foreignKey = @ForeignKey(name = "user_topic_tag"))
    private Topic topic;

    @OneToOne
    @JoinColumn(name = "curriculum_id", foreignKey = @ForeignKey(name = "user_curriculum_tag"))
    private Curriculum curriculum;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_interests_id", foreignKey = @ForeignKey(name = "user_interests"))
    @JsonIgnore
    private User userInterests;

}
