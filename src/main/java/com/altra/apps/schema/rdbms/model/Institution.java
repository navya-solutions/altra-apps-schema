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
@Table(name = "INSTITUTION")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String displayName;
    private String  refId;
    @Lob
    private String bio;
    private boolean active;

    @OneToOne(mappedBy = "institution", optional = false , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Curriculum curriculum;

    @OneToOne
    @JoinColumn(name = "owner",foreignKey = @ForeignKey(name = "institution_owner"))
    private User institutionOwner;

    @OneToMany(mappedBy = "institutionInterests", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Tag> tags = new HashSet<>();

}
