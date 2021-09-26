package com.altra.apps.schema.rdbms.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "VOTE")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long votingPlus, VotingMinus;
    private String userComment;

    @OneToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_vote"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_id", foreignKey = @ForeignKey(name = "block_vote"))
    private Block block;

}
