package com.altra.apps.schema.common;

public enum QuestionTypeEnum {
    FREE_TEXT("FT"),
    MULTIPLE_CHOICE("MC"),
    SINGLE_CHOICE("SC"),
    MULTIPLE_CHOICE_WITH_FREE_TEXT("MC_FT");

    private final String value;

    QuestionTypeEnum(final String value) {
        this.value = value;
    }

    public static QuestionTypeEnum fromValue(final String text) {
        QuestionTypeEnum status = null;
        for (final QuestionTypeEnum b : QuestionTypeEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                status = b;
                break;
            }
        }
        return status;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
