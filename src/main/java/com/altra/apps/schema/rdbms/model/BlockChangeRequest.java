package com.altra.apps.schema.rdbms.model;

import com.altra.apps.schema.common.BlockTypeEnum;
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
@Table(name = "BLOCK_CHANGE_REQUEST")

public class BlockChangeRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String refId;
    private String userRefId;
    @Enumerated(EnumType.STRING)
    private ChangeRequestTypeEnum changeRequestTypeEnum;
    @Enumerated(EnumType.STRING)
    private ChangeRequestStatusTypeEnum changeRequestStatusTypeEnum;
    @Enumerated(EnumType.STRING)
    private BlockTypeEnum changeRequestBlockType;
    private String changeDescription;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_id")
    @JsonIgnore
    private Block block;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BlockChangeRequest)) return false;
        return id != null && id.equals(((BlockChangeRequest) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
