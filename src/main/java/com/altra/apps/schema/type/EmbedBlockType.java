package com.altra.apps.schema.type;

import com.altra.apps.schema.service.BlockTypeEnum;
import lombok.Data;

@Data
public class EmbedBlockType extends BlockType {
    //Link to website the embed block will display.
    private String url;

    @Override
    public BlockTypeEnum getType() {
        return BlockTypeEnum.embed;
    }
}