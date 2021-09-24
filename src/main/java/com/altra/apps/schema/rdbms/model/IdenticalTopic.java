package com.altra.apps.schema.rdbms.model;

import com.altra.apps.schema.common.IdenticalTopicEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "IDENTICAL_TOPIC")
public class IdenticalTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IdenticalTopicEnum type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", foreignKey = @ForeignKey(name = "topic_identical_to_another_topic"))
    @JsonIgnore
    private Topic topic;


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof IdenticalTopic)) return false;
        return id != null && id.equals(((IdenticalTopic) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
