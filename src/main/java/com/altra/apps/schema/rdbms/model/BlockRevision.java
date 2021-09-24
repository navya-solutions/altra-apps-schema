package com.altra.apps.schema.rdbms.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
//@EqualsAndHashCode
@Entity
@Table(name = "BLOCK_REVISION")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
//https://github.com/google/diff-match-patch for diff match patch
//https://neil.fraser.name/writing/diff/

public class BlockRevision implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer revisionNo;
    private Long revisionAt;
    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private Block block;

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
        BlockRevision other = (BlockRevision) obj;
        return Objects.equals(id, other.getId());
    }
}
