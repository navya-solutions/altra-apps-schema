package com.altra.apps.schema.rdbms.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "ROLE")
/**
 * Possible values are:
 * subject advisors( or Content creators),
 * DEPARTMENT_HEAD,
 * PRIVATE_TUTOR,
 * SENIOR_MANAGEMENT,
 * STUDENT,TEACHER
 */
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name, description;


}
