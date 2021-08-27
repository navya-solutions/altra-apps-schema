package com.altra.apps.schema.type;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnnotationType implements Serializable {
    private boolean bold, italic, strikethrough, underline;
    //Whether the text is code style.
    private boolean code;
    private ColorTypeEnum color;
}
