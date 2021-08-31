package com.altra.apps.schema.mongodb.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "subscriptionType")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * Possible values are:
 * FREE,PREMIUM_1_SUBJECT,PREMIUM_INDIVIDUAL,PREMIUM_WHOLE_SCHOOL
 */
public class SubscriptionType implements Serializable {
    @Id
    private Long id;
    private String name, description;


}
