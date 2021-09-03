package com.altra.apps.schema.type;

import com.altra.apps.schema.common.BlockTypeEnum;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class PassageBlockType extends BlockType {
    private BlockTextType title;
    private String author;
    private List<BlockTextType> comment = new LinkedList<>();
    private List<BlockTextType> text = new LinkedList<>();
    private boolean hasLineNumber;
    private String url;

    @Override
    public BlockTypeEnum getType() {
        return BlockTypeEnum.page;
    }
}
