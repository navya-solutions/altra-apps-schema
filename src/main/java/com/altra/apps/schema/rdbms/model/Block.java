package com.altra.apps.schema.rdbms.model;

import com.altra.apps.schema.common.BlockTypeEnum;
import com.altra.apps.schema.type.BlockType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
//@EqualsAndHashCode
@Entity
@Table(name = "BLOCK",indexes = {
        @Index(name = "block_user_created_by_index", columnList = "created_by_id"),
         @Index(name = "block_parent_id_index", columnList = "parent_id"),
})
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
//@Audited
//https://github.com/google/diff-match-patch for diff match patch
//https://neil.fraser.name/writing/diff/

public class Block implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private boolean publiclyAccessible;
    private boolean archived,active;
    private String url;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BlockTypeEnum type;// block type
    @Type(type = "json")
    @Column(columnDefinition = "jsonb", nullable = false)
    private BlockType block;

    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private String note;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "block_create_by"))
    @JsonIgnore
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", foreignKey = @ForeignKey(name = "block_topic"))
    @JsonIgnore
    //@NotAudited
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", foreignKey = @ForeignKey(name = "block_language"))
    @JsonIgnore
    //@NotAudited
    private Language language;

    //@NotAudited
    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<BlockChangeRequest> blockChangeRequests = new HashSet<>();

    //@NotAudited
    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Vote> votes = new HashSet<>();

    // Unix epoch format
    private Long createdTime, lastEditedTime;
    // Copy the source block id to newly created block
    private String refBlockId;

    private boolean hasChildren;
    private Long sequence;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    //@JsonIgnore
    private List<Block> children = new LinkedList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "parent_block"))
    @JsonIgnore
    private Block parent;


    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Block other = (Block) obj;
        return Objects.equals(id, other.getId());
    }
}
