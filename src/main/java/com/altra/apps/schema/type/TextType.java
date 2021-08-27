package com.altra.apps.schema.type;

import lombok.Data;

@Data
public class TextType implements BlockTextType {
    private String content, link;

}
