package com.altra.apps.schema.mongodb.model;

import com.altra.apps.schema.common.ChangeRequestObjectType;
import com.altra.apps.schema.common.ChangeRequestStatusType;
import com.altra.apps.schema.common.ChangeRequestType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "curriculumChangeRequest")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumChangeRequest implements Serializable {
    @Id
    private String id;
    private String refId;
    private String userRefId;
    private ChangeRequestType changeRequestType;
    private ChangeRequestStatusType changeRequestStatusType;
    private ChangeRequestObjectType changeRequestObjectType;
    private String changeDescription;

    @DBRef(lazy = true)
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
