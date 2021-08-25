package com.altra.apps.schema.rdbms.model;

import com.altra.apps.schema.common.SupportedQuestionTypeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "SUPPORTED_QUESTION_TYPE")
public class SupportedQuestionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private SupportedQuestionTypeEnum supportedQuestionType;

}
