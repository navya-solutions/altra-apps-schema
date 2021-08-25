package com.altra.apps.schema.rdbms.model;

import com.altra.apps.schema.common.ChangeRequestObjectType;
import com.altra.apps.schema.common.ChangeRequestStatusType;
import com.altra.apps.schema.common.ChangeRequestType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String pid;
    private String refId;
    private String userRefId;
    @Enumerated(EnumType.STRING)
    private ChangeRequestType changeRequestType;
    @Enumerated(EnumType.STRING)
    private ChangeRequestStatusType changeRequestStatusType;
    @Enumerated(EnumType.STRING)
    private ChangeRequestObjectType changeRequestObjectType;
    private String changeDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Curriculum_id")
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
