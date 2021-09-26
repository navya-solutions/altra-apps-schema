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
@Table(name = "USER_CURRICULUM_ACCESS")
public class UserCurriculumAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_curriculum_access_id", foreignKey = @ForeignKey(name = "user_curriculum_access_user"))
    @JsonIgnore
    private User userCurriculumAccess;

    @OneToOne
    @JoinColumn(name = "curriculum_id", foreignKey = @ForeignKey(name = "user_curriculum_access_curriculum"))
    private Curriculum curriculum;

    private boolean hasVerified; //true, if the user is verified for the curriculum
    private boolean hasActive;



}
