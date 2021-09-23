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
@Table(name = "SUBSCRIPTION_TYPE")
/**
 * Possible values are:
 * FREE,PREMIUM_1_SUBJECT,PREMIUM_INDIVIDUAL,PREMIUM_WHOLE_SCHOOL
 */
public class SubscriptionType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;
    private String name, description;


}
