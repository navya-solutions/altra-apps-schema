package com.altra.apps.schema.type;

import com.altra.apps.schema.common.BlockTypeEnum;
import com.altra.apps.schema.common.QuestionTypeEnum;
import com.altra.apps.schema.common.SupportedQuestionTypeEnum;
import com.altra.apps.schema.type.answer.AnswerType;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class QuestionBlockType extends BlockType {
    private List<BlockTextType> text = new LinkedList<>();
    private List<BlockType> children = new LinkedList<>();
    private SupportedQuestionTypeEnum supportedQuestionType;
    private List<QuestionTypeEnum> questionType = new LinkedList<>();
    private AnswerType answer;
    private int mark, page;
    private List<FileObjectType> externalLink = new LinkedList<>();
    private MarkingSchemaType markingSchema;


    @Override
    public BlockTypeEnum getType() {
        return BlockTypeEnum.question;
    }
}
