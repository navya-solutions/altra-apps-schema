package com.altra.apps.schema.example;

import com.altra.apps.schema.common.CustomUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

public class Curriculum {
    private final Set<String> topicLabel = Set.of("Subject", "Section", "Part", "Key issue level");

    public static void main(String[] args) {
        Curriculum curriculum = new Curriculum();
        //curriculum.topicLabel.stream().forEach(label -> {
        //System.out.println(label);

        // create topic labels
        Set<String> topicLabels = Set.of("Subject", "Section", "Part", "Key issue level");

        //----Topic Labels --> Subject ---------------------------------------

        // create levels for topic level 1st element i.e. subject
        Level subjectLevel1 = new Level("NATIONAL_5", "Subject");
        Level subjectLevel2 = new Level("HIGHER", "Subject");
        Level subjectLevel3 = new Level("Avd. HIGHER", "Subject");

        // create topic for topic level 1st element i.e. subject
        Topic subjectTopicMaths = new Topic("MATHS", "Subject");
        Topic subjectTopicHistory = new Topic("HISTORY", "Subject");

        // create unit which link topic with levels
        // one of the root unit
        Unit rootUnit = getUnit(subjectLevel1, subjectTopicMaths);

        //----Topic Labels --> Section ---------------------------------------

        // create levels for topic level 2nd element i.e. Section
        Level sectionLevel1 = new Level("Easy", "Section");
        Level sectionLevel2 = new Level("Medium", "Section");
        Level sectionLevel3 = new Level("Hard", "Section");

        // create topic for topic level 1st element i.e. subject
        Topic subjectTopicGeometry = new Topic("Geometry", "Section");
        Topic subjectTopicAlgebra = new Topic("Algebra", "Section");


        // one of the section unit
        Unit sectionUnit1 = getUnit(sectionLevel1, subjectTopicGeometry);
        Unit sectionUnit2 = getUnit(sectionLevel2, subjectTopicAlgebra);


        //----Topic Labels --> Parts ---------------------------------------

        // create levels for topic level 3nd element i.e. part
        Level partLevel1 = new Level("Easy", "Section");
        Level partLevel2 = new Level("Medium", "Section");
        Level partLevel3 = new Level("Hard", "Section");

        // create topic for topic level 1st element i.e. subject
        Topic partTopicCircles = new Topic("Circles", "Section");
        Topic partTopicPythagoras = new Topic("Pythagoras", "Section");

        // link parts unit to section unit -- NOT TO THE SUBJECT UNITs
        Unit partUnit1 = getUnit(partLevel1, partTopicCircles);
        Unit partUnit2 = getUnit(partLevel1, partTopicPythagoras);

        //----linking between different Topic Labels  ---------------------------------------

        // link "part" topic labels to "section"
        sectionUnit2.addChildUnit(partUnit1);
        sectionUnit2.addChildUnit(partUnit2);
        // link "section" topic labels to "subject"
        rootUnit.addChildUnit(sectionUnit1);
        rootUnit.addChildUnit(sectionUnit2);


        System.out.println(rootUnit);
        CustomUtils.ObjectToJson(rootUnit);

    }

    private static Unit getUnit(Level subjectLevel1, Topic subjectTopicMaths) {
        Unit rootUnit = new Unit();
        rootUnit.setTopic(subjectTopicMaths);
        rootUnit.setLevel(subjectLevel1);
        rootUnit.setTitle(String.format("%s-%s", subjectTopicMaths.getTitle(), subjectLevel1.getTitle()));
        return rootUnit;
    }
}

@Setter
@Getter
@AllArgsConstructor
class Level {
    private String title;
    private String topicLabel;
}

@Setter
@Getter
@ToString
class Unit {
    private String title;
    private Set<Unit> childUnits = new HashSet<>();
    @ToString.Exclude
    private Topic topic;
    @ToString.Exclude
    private Level level;
    @ToString.Exclude
    @JsonIgnore
    private Unit parentUnit;

    public void addChildUnit(Unit unit) {
        this.childUnits.add(unit);
        unit.setParentUnit(this);
    }
}

@Setter
@Getter
@AllArgsConstructor
class Topic {
    private String title;
    private String topicLabel;
    /*private Set<Level> levels = new HashSet<>();

    public void addLevel(String title) {
        Level level = new Level();
        level.setTitle(title);
        this.levels.add(level);
    }*/


}