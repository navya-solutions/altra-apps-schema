package com.altra.apps.schema.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tag")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * Possible values are:
 * DEPARTMENT_HEAD,PRIVATE_TUTOR,SENIOR_MANAGEMENT,STUDENT,TEACHER
 */
//TODO: fields need to be corrected
public class Tag {
    @Id
    private String id;
    private String name;
    @DBRef(lazy = true)
    private Topic topicInterests;
    @DBRef(lazy = true)
    private Topic levelInterests;

}
