package com.altra.apps.schema.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * Possible values are:
 * subject advisors( or Content creators),
 * DEPARTMENT_HEAD,
 * PRIVATE_TUTOR,
 * SENIOR_MANAGEMENT,
 * STUDENT,TEACHER
 */
public class Role implements Serializable {
    @Id
    private String id;
    private String name, description;


}
