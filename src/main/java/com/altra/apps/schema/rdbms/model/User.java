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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name, displayName;
    @Lob
    private String bioData;
    private String avatarUrl;
    @Column(unique = true, nullable = false)
    private String email, phoneNumber;
    // TODO: one-to-one Or one-to-many ??
    @OneToOne
    @JoinColumn(name = "role_id",  foreignKey = @ForeignKey(name = "user_role"))
    private Role role;
    @OneToOne
    @JoinColumn(name = "institution_id",foreignKey = @ForeignKey(name = "user_institution"))
    private Institution institution;
    @OneToOne
    @JoinColumn(name = "subscription_id", foreignKey = @ForeignKey(name = "user_subscription"))
    private Subscription Subscription;

    @OneToMany(mappedBy = "userInterests", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Tag> tags = new HashSet<>();
    private Long createdTime, lastEditedTime;

}
