package com.altra.apps.schema.type;

import com.altra.apps.schema.common.BlockTypeEnum;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class HeadingOneBlockType extends BlockType {
    private List<BlockTextType> text = new LinkedList<>();

    @Override
    public BlockTypeEnum getType() {
        return BlockTypeEnum.heading_1;
    }
}
