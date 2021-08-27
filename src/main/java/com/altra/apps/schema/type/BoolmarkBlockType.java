package com.altra.apps.schema.type;

import com.altra.apps.schema.service.BlockTypeEnum;
import lombok.Data;

@Data
public class BoolmarkBlockType extends BlockType {
    //Link to website the embed block will display.
    private FileObjectType text;

    @Override
    public BlockTypeEnum getType() {
        return BlockTypeEnum.bookmark;
    }
}
