package com.altra.apps.schema.type;

import lombok.Data;

@Data
public class FileObjectType {
    //Type of this file object. Possible values are: "external", "file".
    private String type;
    private String url;

}
