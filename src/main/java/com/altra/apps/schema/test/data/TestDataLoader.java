package com.altra.apps.schema.test.data;

import com.altra.apps.schema.common.CustomUtils;
import com.altra.apps.schema.type.*;

import java.util.List;
import java.util.Set;

public interface TestDataLoader {

    void testDataLoader();

    default PageBlockType getPageBlockType() {
        final PageBlockType pageBlockType = new PageBlockType();
        pageBlockType.setHasChildren(true);
        pageBlockType.setId(CustomUtils.getUniqueId());
        pageBlockType.setText(List.of(getTextType("link", "content")));
        pageBlockType.setChildren(List.of(getHeadingOneBlockType(), getTodoBlockType()));
        return pageBlockType;
    }

    default HeadingOneBlockType getHeadingOneBlockType() {
        HeadingOneBlockType headingOneBlockType = new HeadingOneBlockType();
        headingOneBlockType.setHasChildren(false);
        headingOneBlockType.setId(CustomUtils.getUniqueId());
        headingOneBlockType.setText(List.of(getTextType("link", "content")));
        return headingOneBlockType;
    }

    default TextType getTextType(String link, String header) {
        TextType textType = new TextType();
        textType.setLink(link);
        textType.setContent(header);
        return textType;
    }

    default TodoBlockType getTodoBlockType() {
        TodoBlockType todoBlockType = new TodoBlockType();
        todoBlockType.setHasChildren(true);
        todoBlockType.setId(CustomUtils.getUniqueId());
        RichTextType textType = getRichTextType();
        todoBlockType.setText(List.of(textType));
        todoBlockType.setChildren(List.of(getHeadingOneBlockType()));
        return todoBlockType;
    }

    default RichTextType getRichTextType() {
        RichTextType textType = new RichTextType();
        textType.setPlainText("plain-text");
        AnnotationType annotationType = new AnnotationType();
        annotationType.setBold(true);
        textType.setAnnotations(Set.of(annotationType));
        textType.setHref("href");
        return textType;
    }
}
