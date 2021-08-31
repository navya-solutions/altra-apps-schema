package com.altra.apps.schema.mongodb.model;

import com.altra.apps.schema.common.BlockTypeEnum;
import com.altra.apps.schema.mongodb.type.LanguageType;
import com.altra.apps.schema.type.BlockType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Document(collection = "block")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Block implements Serializable {
    @Id
    private String id;
    private boolean hasPublicAccess;
    private boolean archived;
    private String url;
    private BlockTypeEnum blockType;// block type
    private BlockType block;

    @DBRef(lazy = true)
    private Topic topic;

    private Set<LanguageType> languages = new HashSet<>();
    // Unix epoch format
    private Long createdTime, lastEditedTime;


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
