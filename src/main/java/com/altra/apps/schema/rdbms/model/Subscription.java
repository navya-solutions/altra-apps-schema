package com.altra.apps.schema.rdbms.model;

import com.altra.apps.schema.common.SubscriptionTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
/**
 * Possible values are:
 * FREE,PREMIUM_1_SUBJECT,PREMIUM_INDIVIDUAL,PREMIUM_WHOLE_SCHOOL
 */
@Table(name = "SUBSCRIPTION")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SubscriptionTypeEnum type;
    private String description;
    private double monthlyPrice, quarterlyPrice, yearlyPrice;
    private double discount;


}
