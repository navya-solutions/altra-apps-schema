package com.altra.apps.schema.rdbms.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String pid;
    private String name, displayName;
    // TODO: one-to-one Or one-to-many ??
    @OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    @OneToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;
    @OneToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    private SubscriptionType SubscriptionType;

    @OneToMany(mappedBy = "topicInterests", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Tag> topicInterests = new HashSet<>();
    @OneToMany(mappedBy = "levelInterests", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Tag> levelInterests = new HashSet<>();
}
