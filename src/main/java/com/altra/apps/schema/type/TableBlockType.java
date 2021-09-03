package com.altra.apps.schema.type;

import com.altra.apps.schema.common.BlockTypeEnum;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class TableBlockType extends BlockType {
    private List<BlockTextType> text = new LinkedList<>();
    // stores rows
    private List<BlockType> children = new LinkedList<>();


    @Override
    public BlockTypeEnum getType() {
        return BlockTypeEnum.page;
    }
}
