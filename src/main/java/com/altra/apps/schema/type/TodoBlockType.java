package com.altra.apps.schema.type;

import com.altra.apps.schema.common.BlockTypeEnum;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class TodoBlockType extends BlockType {
    private List<BlockTextType> text = new LinkedList<>();
    private List<BlockType> children = new LinkedList<>();
    private boolean checked;

    @Override
    public BlockTypeEnum getType() {
        return BlockTypeEnum.to_do;
    }
}
