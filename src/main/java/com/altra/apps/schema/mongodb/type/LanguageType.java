package com.altra.apps.schema.mongodb.type;


import lombok.Data;

import java.io.Serializable;

@Data
public class LanguageType implements Serializable {
    private String shortCode, title;
}
