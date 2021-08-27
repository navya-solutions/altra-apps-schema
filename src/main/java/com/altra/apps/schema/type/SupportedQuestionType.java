package com.altra.apps.schema.type;

import com.altra.apps.schema.common.SupportedQuestionTypeEnum;

import javax.persistence.*;


public class SupportedQuestionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private SupportedQuestionTypeEnum supportedQuestionType;

}
