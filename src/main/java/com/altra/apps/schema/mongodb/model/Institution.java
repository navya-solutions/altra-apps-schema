package com.altra.apps.schema.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Document(collection = "institution")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Institution implements Serializable {
    @Id
    private String id;
    private String name, refId, description;

    @DBRef(lazy = true)
    private Set<Curriculum> curriculum = new HashSet<>();

}
