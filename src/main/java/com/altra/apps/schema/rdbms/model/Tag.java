package com.altra.apps.schema.rdbms.model;

import com.altra.apps.schema.common.TagTypeEnum;
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

    @Column(nullable = false)
    private String tagName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TagTypeEnum type;

    @Column(nullable = false)
    private Integer typeRefId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_interests_id", foreignKey = @ForeignKey(name = "user_interests"))
    @JsonIgnore
    private User userInterests;

}
