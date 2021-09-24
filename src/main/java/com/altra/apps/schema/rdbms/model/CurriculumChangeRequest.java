package com.altra.apps.schema.rdbms.model;

import com.altra.apps.schema.common.ChangeRequestObjectTypeEnum;
import com.altra.apps.schema.common.ChangeRequestStatusTypeEnum;
import com.altra.apps.schema.common.ChangeRequestTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "CURRICULUM_CHANGE_REQUEST")

public class CurriculumChangeRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private Integer refObjectId;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ChangeRequestObjectTypeEnum objectType;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false, foreignKey = @ForeignKey(name = "user_curriculum_change_request"))
    private User user;

    @Enumerated(EnumType.STRING)
    private ChangeRequestTypeEnum type;
    @Enumerated(EnumType.STRING)
    private ChangeRequestStatusTypeEnum status;
    @Lob
    private String changeDescription;
    private String comment;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id", foreignKey = @ForeignKey(name = "curriculum_change_request"))
    @JsonIgnore
    private Curriculum curriculum;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CurriculumChangeRequest)) return false;
        return id != null && id.equals(((CurriculumChangeRequest) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
