package com.altra.apps.schema.type;

import com.altra.apps.schema.common.BlockTypeEnum;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class BulletedListItemBlockType extends BlockType {
    private List<BlockTextType> text = new LinkedList<>();
    private List<BlockType> children = new LinkedList<>();

    @Override
    public BlockTypeEnum getType() {
        return BlockTypeEnum.bulleted_list_item;
    }
}
