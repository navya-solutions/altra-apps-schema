package com.altra.apps.schema.common;

public enum SubjectTypeEnum {
    MATHS("MATHS"),
    SCIENCE("SCIENCE"),
    ENGLISH("ENGLISH"),
    COMPUTER("COMPUTER");

    private final String value;

    SubjectTypeEnum(final String value) {
        this.value = value;
    }

    public static SubjectTypeEnum fromValue(final String text) {
        SubjectTypeEnum status = null;
        for (final SubjectTypeEnum b : SubjectTypeEnum.values()) {
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
