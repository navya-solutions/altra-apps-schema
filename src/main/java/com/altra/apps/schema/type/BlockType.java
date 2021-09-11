package com.altra.apps.schema.type;

import com.altra.apps.schema.common.BlockTypeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BlockType implements Serializable {
    String id;
    BlockTypeEnum type;
    String createdTime, lastEditedTime;
    boolean hasChildren;


    public abstract BlockTypeEnum getType();
}
