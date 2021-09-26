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
@Table(name = "USER_INVITATION")
public class UserInvitation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String invitedUserEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_invitation_id", foreignKey = @ForeignKey(name = "user_user_invitation"))
    @JsonIgnore
    private User userInvitation;

    private String curriculumIds; //comma separated curriculum list
    private String topicIds;//comma separated curriculum list
    private Long createdTime;


}
