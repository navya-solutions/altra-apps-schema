package com.altra.apps.schema.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String name, displayName;
    private Role role;
    @DBRef(lazy = true)
    private Institution institution;
    private com.altra.apps.schema.mongodb.type.SubscriptionType SubscriptionType;
    @DBRef(lazy = true)
    private Set<Tag> topicInterests = new HashSet<>();
    @DBRef(lazy = true)
    private Set<Tag> levelInterests = new HashSet<>();
}
