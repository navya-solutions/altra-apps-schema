package com.altra.apps.schema.type.answer;

import com.altra.apps.schema.common.AnswerTypeEnum;
import com.altra.apps.schema.type.BlockTextType;
import com.altra.apps.schema.type.FileObjectType;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class MultipleChoiceAnswerType extends AnswerType {
    private List<BlockTextType> text = new LinkedList<>();
    private List<FileObjectType> images = new LinkedList<>();


    @Override
    public AnswerTypeEnum getType() {
        return AnswerTypeEnum.multiple_choice;
    }


}
