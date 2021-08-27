package com.altra.apps.schema.type;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RichTextType implements BlockTextType {
    private String plainText, href;
    private Set<AnnotationType> annotations = new HashSet<>();
    //Type of this rich text object. Possible values are: "text", "mention", "equation".
    private String type;
}
