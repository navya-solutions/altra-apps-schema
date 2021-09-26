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
@Table(name = "USER_COMMENT")
public class UserComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "user_comment_change_request"))
    private User user;
    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_cr_id", foreignKey = @ForeignKey(name = "user_comment_curriculum_change_request"))
    @JsonIgnore
    private CurriculumChangeRequest curriculumChangeRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_cr_id", foreignKey = @ForeignKey(name = "user_comment_block_change_request"))
    @JsonIgnore
    private BlockChangeRequest blockChangeRequest;

}
