package com.altra.apps.schema.type.answer;

import com.altra.apps.schema.common.AnswerTypeEnum;
import com.altra.apps.schema.type.BlockTextType;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class FreeTextAnswerType extends AnswerType {
    private List<BlockTextType> text = new LinkedList<>();
    private int textLengthLimit;


    @Override
    public AnswerTypeEnum getType() {
        return AnswerTypeEnum.free_text;
    }


}
