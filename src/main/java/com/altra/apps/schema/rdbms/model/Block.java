package com.altra.apps.schema.rdbms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
//@EqualsAndHashCode
@Entity
@Table(name = "BLOCK")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Block {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String pid;
    private boolean publiclyAccessible;
    private boolean archived;
    //might be used for linking different blocks
    private String url;

    //TODO: will added later
    //private User author;
    //TODO: "edit history" do we need it as the block will be copied for new user
    //private User author;
    @OneToMany(mappedBy = "parentBlock", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Block> childBlocks = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Block parentBlock;
    // Unix epoch format
    private Long createdTime, lastEditedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    @JsonIgnore
    private Unit unit;

    public void addChildBlock(Block block) {
        block.setParentBlock(this);
        this.childBlocks.add(block);

    }

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
