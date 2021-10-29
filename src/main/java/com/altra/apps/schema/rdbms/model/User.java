package com.altra.apps.schema.rdbms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String displayName;
    @Lob
    private String bioData;
    private String avatarUrl;
    @Column(unique = true, nullable = false)

    private String email;
    private boolean active;
    private boolean verified;
    private String school;

    @OneToOne
    @JoinColumn(name = "institution_id",foreignKey = @ForeignKey(name = "user_institution"))
    private Institution institution;


    @OneToOne
    @JoinColumn(name = "subscription_id", foreignKey = @ForeignKey(name = "user_subscription"))
    private Subscription Subscription;

    @OneToMany(mappedBy = "userInterests", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "userInvitation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<UserInvitation> userInvitations = new HashSet<>();

    @OneToMany(mappedBy = "userCurriculumAccess", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<UserCurriculumAccess> userCurriculumAccesses = new HashSet<>();

    @OneToMany(mappedBy = "userTopicAccess", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<UserTopicAccess> userTopicAccesses = new HashSet<>();

    private Long createdTime, lastEditedTime;

}
