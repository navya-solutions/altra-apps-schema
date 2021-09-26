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
@Table(name = "USER_TOPIC_ACCESS")
public class UserTopicAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_topic_access_id", foreignKey = @ForeignKey(name = "user_topic_access_user"))
    @JsonIgnore
    private User userTopicAccess;

    @OneToOne
    @JoinColumn(name = "topic_id", foreignKey = @ForeignKey(name = "user_topic_access_topic"))
    private Topic topic;

    private boolean hasVerified; //true, if the user is verified for the topic
    private boolean hasActive;

}
