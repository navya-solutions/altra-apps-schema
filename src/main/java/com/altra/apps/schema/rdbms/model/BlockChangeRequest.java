package com.altra.apps.schema.rdbms.model;

import com.altra.apps.schema.common.BlockTypeEnum;
import com.altra.apps.schema.common.ChangeRequestStatusTypeEnum;
import com.altra.apps.schema.common.ChangeRequestTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "BLOCK_CHANGE_REQUEST")

public class BlockChangeRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String refObjectId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BlockTypeEnum blockType;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "user_block_change_request"))
    private User requester;

    private String comment;

    @OneToMany(mappedBy = "blockChangeRequest", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<UserComment> userComments = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private ChangeRequestTypeEnum type;

    @Enumerated(EnumType.STRING)
    private ChangeRequestStatusTypeEnum status;

    @Lob
    @Column(nullable = false)
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
