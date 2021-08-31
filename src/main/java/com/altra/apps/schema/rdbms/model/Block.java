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

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
//@EqualsAndHashCode
@Entity
@Table(name = "BLOCK")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
public class Block implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String pid;
    private boolean hasPublicAccess;
    private boolean archived;
    private String url;
    @Enumerated(EnumType.STRING)
    private BlockTypeEnum blockType;// block type
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private BlockType block;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    @JsonIgnore
    private Unit unit;

    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Language> languages = new HashSet<>();
    // Unix epoch format
    private Long createdTime, lastEditedTime;

    public void addLanguage(Language language) {
        this.languages.add(language);
        language.setBlock(this);
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
