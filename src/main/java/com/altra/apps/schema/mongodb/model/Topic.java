package com.altra.apps.schema.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


@Document(collection = "topic")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Topic implements Serializable {
    boolean hasChildren;
    @Id
    private String id;
    private String title, description;
    //TODO: need more details how this field will be used??
    private String sameAs, similarTo;
    private String label;
    @DBRef(lazy = true)
    private List<Topic> children = new LinkedList<>();
    // @ToString.Exclude
    //@JsonIgnore
    //private Topic parent;
    @DBRef(lazy = true)
    private Curriculum curriculum;

    @DBRef(lazy = true)
    private List<Block> blocks = new LinkedList<>();

    public void addChildTopic(Topic topic) {
        this.children.add(topic);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Topic)) return false;
        return id != null && id.equals(((Topic) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
