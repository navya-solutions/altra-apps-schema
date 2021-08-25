package com.altra.apps.schema;

import com.altra.apps.schema.common.*;
import com.altra.apps.schema.rdbms.model.*;
import com.altra.apps.schema.service.CurriculumService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Set;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ProjectTestDataLoader {

    private final CurriculumService curriculumService;

    public void loadTestData() {

        final Set<Curriculum> curriculumCounter = curriculumService.getAll();
        if (curriculumCounter != null && curriculumCounter.size() > 1)
            // return if the records are in DB already!!
            return;
        // create curriculum
        Curriculum curriculum = new Curriculum();
        curriculum.setPid(getUniqueId());
        curriculum.addCountry(getCountry());
        curriculum.setDescription("Curriculum for Excellence places learners at the heart of education. At its centre are four fundamental capacities...");
        curriculum.setTitle("Curriculum for Excellence");
        curriculum.setShortTitle("CFE");

        final CurriculumChangeRequest changeRequest = getCurriculumChangeRequest();
        curriculum.addCurriculumSuggestions(changeRequest);

        // create curriculum topic labels
        final TopicLabel topicLabel_level1 = getTopicLabel(1, "Subject",
                Set.of("MATHS", "HISTORY"),
                Set.of("NATIONAL_5", "HIGHER", "Avd. HIGHER"));
        curriculum.addTopicLabel(topicLabel_level1);

        final TopicLabel topicLabel_level2 = getTopicLabel(2, "Section",
                Set.of("Geometry", "Algebra", "Statistics"),
                Set.of("Easy", "Medium", "Avd. Hard"));
        curriculum.addTopicLabel(topicLabel_level2);

        final TopicLabel topicLabel_level3 = getTopicLabel(3, "Parts",
                Set.of("Circles", "Pythagoras"),
                Set.of("Easy", "Medium", "Avd. Hard"));
        curriculum.addTopicLabel(topicLabel_level3);

        final TopicLabel topicLabel_level4 = getTopicLabel(4, "Key issue level",
                Set.of("Straight Line", "Quartiles"),
                Set.of("Easy", "Medium", "Avd. Hard"));
        curriculum.addTopicLabel(topicLabel_level4);

        final TopicLabel topicLabel = curriculum.getTopicLabels()
                .stream()
                .max(Comparator.comparing(TopicLabel::getSequence))
                .get();

        final Unit unit_level1 = getUnit(topicLabel_level1.getLevels().stream().findAny().get(), topicLabel_level1.getTopics().stream().findAny().get());
        final Unit unit_level2 = getUnit(topicLabel_level2.getLevels().stream().findAny().get(), topicLabel_level2.getTopics().stream().findAny().get());
        final Unit unit_level3 = getUnit(topicLabel_level3.getLevels().stream().findAny().get(), topicLabel_level3.getTopics().stream().findAny().get());
        final Unit unit_level4 = getUnit(topicLabel_level4.getLevels().stream().findAny().get(), topicLabel_level4.getTopics().stream().findAny().get());

        ResourceBlock block1 = getResourceBlock();
        unit_level4.addBlock(block1);
        ResourceBlock block2 = getResourceBlock();
        unit_level4.addBlock(block2);

        unit_level3.addChildUnit(unit_level4);
        unit_level2.addChildUnit(unit_level3);
        unit_level1.addChildUnit(unit_level2);

        curriculum.addUnit(unit_level1);
        curriculum.addUnit(unit_level2);
        curriculum.addUnit(unit_level3);
        curriculum.addUnit(unit_level4);


        final Curriculum curriculum1 = curriculumService.addCurriculum(curriculum);
        CustomUtils.ObjectToJson(curriculum1);

    }

    private CurriculumChangeRequest getCurriculumChangeRequest() {
        final CurriculumChangeRequest changeRequest = new CurriculumChangeRequest();
        changeRequest.setChangeRequestType(ChangeRequestType.UPDATE);
        changeRequest.setChangeRequestObjectType(ChangeRequestObjectType.LEVEL);
        changeRequest.setChangeRequestStatusType(ChangeRequestStatusType.SUBMITTED);
        changeRequest.setRefId(getUniqueId());
        changeRequest.setUserRefId(getUniqueId());
        changeRequest.setChangeDescription("Change Level name NATIONAL_5 to NATIONAL_6");
        changeRequest.setPid(getUniqueId());
        return changeRequest;
    }

    private Unit getUnit(Level subjectLevel1, Topic subjectTopicMaths) {
        Unit unit = new Unit();
        unit.setPid(getUniqueId());
        unit.setTopic(subjectTopicMaths);
        unit.setLevel(subjectLevel1);
        final String title = String.format("%s-%s", subjectTopicMaths.getTitle(), subjectLevel1.getTitle());
        unit.setTitle(title);
        unit.setDescription(String.format("%s description", title));

        return unit;
    }

    private TopicLabel getTopicLabel(int Sequence, String topicLabelTitle, Set<String> topicTitles, Set<String> levelTitles) {
        final TopicLabel topicLabel = new TopicLabel();
        topicLabel.setSequence(Sequence);
        topicLabel.setPid(getUniqueId());
        topicLabel.setTitle(topicLabelTitle);

        topicTitles.forEach(t -> {
            Topic topic = getTopic(t);
            topicLabel.addTopic(topic);
        });

        levelTitles.forEach(t -> {
            Level level = getLevel(t);
            topicLabel.addLevel(level);
        });

        return topicLabel;
    }

    private Level getLevel(String title) {
        Level level = new Level();
        level.setPid(getUniqueId());
        level.setDescription(String.format("%s description", title));
        level.setTitle(title);
        return level;
    }

    private Topic getTopic(String title) {
        Topic topic = new Topic();
        topic.setPid(getUniqueId());
        topic.setDescription(String.format("%s description", title));
        topic.setTitle(title);
        return topic;
    }

    private ResourceBlock getResourceBlock() {
        ResourceBlock block = new ResourceBlock();
        block.setTitle("2015 Past Paper");
        block.setDescription("The official SQA 2015 Past Paper...");
        block.setExportFormat(ExportFormatType.PDF);
        block.setCreatedTime(CustomUtils.getEpochCurrentTime());
        block.setLastEditedTime(CustomUtils.getEpochCurrentTime());
        block.setPid(getUniqueId());
        final Language language = new Language();
        language.setTitle("ENGLISH");
        block.addLanguage(language);

        HeadingBlock headingBlock = new HeadingBlock();
        headingBlock.setTitle("1 Hour and 15 minutes");
        headingBlock.setPid(getUniqueId());
        headingBlock.setCreatedTime(CustomUtils.getEpochCurrentTime());
        headingBlock.setLastEditedTime(CustomUtils.getEpochCurrentTime());
        block.addChildBlock(headingBlock);


        SubHeadingBlock subHeadingBlock = new SubHeadingBlock();
        subHeadingBlock.setTitle("1 Hour and 15 minutes");
        subHeadingBlock.setPid(getUniqueId());
        subHeadingBlock.setCreatedTime(CustomUtils.getEpochCurrentTime());
        subHeadingBlock.setLastEditedTime(CustomUtils.getEpochCurrentTime());
        block.addChildBlock(subHeadingBlock);
        return block;
    }

    private Country getCountry() {
        final Country country = new Country();
        country.setPid(getUniqueId());
        country.setTitle("SCOTLAND");
        return country;
    }

    private String getUniqueId() {
        return UUID.randomUUID().toString();
    }


}
