package com.altra.apps.schema.type;

import com.altra.apps.schema.service.BlockTypeEnum;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class HeadingThreeBlockType extends BlockType {
    private List<BlockTextType> text = new LinkedList<>();

    @Override
    public BlockTypeEnum getType() {
        return BlockTypeEnum.heading_3;
    }
}
