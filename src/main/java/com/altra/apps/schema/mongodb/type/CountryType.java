package com.altra.apps.schema.mongodb.type;

import lombok.Data;

import java.io.Serializable;

@Data
public class CountryType implements Serializable {
    private String country, shortCode;
}
