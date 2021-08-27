package com.altra.apps.schema.type;

import com.altra.apps.schema.service.BlockTypeEnum;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class PageBlockType extends BlockType {
    private List<BlockTextType> text = new LinkedList<>();
    private List<BlockType> children = new LinkedList<>();
    private FileObjectType icon;
    private FileObjectType cover;
    private String url;

    @Override
    public BlockTypeEnum getType() {
        return BlockTypeEnum.page;
    }
}
