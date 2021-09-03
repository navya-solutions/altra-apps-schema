package com.altra.apps.schema.type.answer;

import com.altra.apps.schema.common.AnswerTypeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AnswerType implements Serializable {
    String id;
    AnswerTypeEnum type;

    public abstract AnswerTypeEnum getType();
}
