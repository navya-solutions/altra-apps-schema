package com.altra.apps.schema.example;

import com.altra.apps.schema.common.CustomUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Data
public class CurriculumDataModel2 implements Serializable {

    private final List<Topic1> topics = new LinkedList<>();

    public static void main(String[] args) {
        CurriculumDataModel2 curriculum = new CurriculumDataModel2();

        //----Topic Labels --> Subject ---------------------------------------
        Topic1 subjectTopicMaths = new Topic1("Maths", "Subject", true);

        //----Topic Labels --> Section ---------------------------------------
        Topic1 sectionLevel1 = new Topic1("NATIONAL_5", "Section", true);
        Topic1 sectionLevel2 = new Topic1("HIGHER", "Section", false);
        Topic1 sectionLevel3 = new Topic1("Avd. HIGHER", "Section", false);

        //----Topic Labels --> Parts ---------------------------------------
        Topic1 partLevel1 = new Topic1("Statistics", "Part", true);
        Topic1 partLevel2 = new Topic1("Geometry", "Part", false);
        Topic1 partLevel3 = new Topic1("Algebra", "Part", false);

        //----Topic Labels --> Key Issue Level ---------------------------------------
        Topic1 KeyIssueLevel1 = new Topic1("Circles", "Key Issue Level", false);
        Topic1 KeyIssueLevel2 = new Topic1("Pythagoras", "Key Issue Level", false);

        //----linking between different Topic Labels  ---------------------------------------
        partLevel1.addChildTopic(KeyIssueLevel1);
        partLevel1.addChildTopic(KeyIssueLevel2);

        sectionLevel1.addChildTopic(partLevel1);
        sectionLevel1.addChildTopic(partLevel2);
        sectionLevel1.addChildTopic(partLevel3);

        subjectTopicMaths.addChildTopic(sectionLevel1);
        subjectTopicMaths.addChildTopic(sectionLevel2);
        subjectTopicMaths.addChildTopic(sectionLevel3);


        Topic1 subjectTopicHistory = new Topic1("History", "Subject", false);
        Topic1 subjectTopicScience = new Topic1("Science", "Subject", false);

        curriculum.topics.addAll(List.of(subjectTopicMaths, subjectTopicHistory, subjectTopicScience));


        System.out.println(curriculum);
        CustomUtils.ObjectToJson(curriculum);

    }


}


@Data
class Topic1 implements Serializable {
    boolean hasChildren;
    private String title;
    private String label;
    private List<Topic1> children = new LinkedList<>();
    @ToString.Exclude
    @JsonIgnore
    private Topic1 parent;
    public Topic1(String title, String label, boolean hasChildren) {
        this.title = title;
        this.label = label;
        this.hasChildren = hasChildren;
    }

    public void addChildTopic(Topic1 topic1) {
        this.children.add(topic1);
        topic1.setParent(this);
    }


}