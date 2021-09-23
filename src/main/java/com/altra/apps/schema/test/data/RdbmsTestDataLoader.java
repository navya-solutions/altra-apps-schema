package com.altra.apps.schema.test.data;

import com.altra.apps.schema.common.*;
import com.altra.apps.schema.rdbms.model.*;
import com.altra.apps.schema.rdbms.service.CurriculumService;
import com.altra.apps.schema.type.PageBlockType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RdbmsTestDataLoader implements TestDataLoader {

    private final CurriculumService curriculumService;
    //private final CurriculumElasticSearchRepository curriculumElasticSearchRepository;


    @Override
    public void testDataLoader() {
        generateRDBMSTestData();

    }

    private TopicLabel getTopicLabel(int Sequence, String topicLabelTitle) {
        final TopicLabel topicLabel = new TopicLabel();
        topicLabel.setSequence(Sequence);
        topicLabel.setTitle(topicLabelTitle);
        return topicLabel;
    }

    private void generateRDBMSTestData() {
        final Set<Curriculum> curriculumCounter = curriculumService.getAll();
        if (curriculumCounter != null && curriculumCounter.size() > 1) {
            // return if the records are in DB already!!
            return;
        }


        // create curriculum
        Curriculum curriculum = new Curriculum();
        //curriculum.setCountry(getCountry());
        curriculum.setHasPublicAccess(true);
        curriculum.setDescription("Curriculum for Excellence places learners at the heart of education. At its centre are four fundamental capacities...");
        curriculum.setTitle("Curriculum for Excellence");
        curriculum.setShortTitle("CFE");
        //curriculum.setCurriculumTopicLabels(CurriculumTopicLabelsEnum.SUBJECT_TOPIC_KEYISSUES_EXPLANATION);


        // create curriculum topic labels
        final TopicLabel topicLabel_level1 = getTopicLabel(1, "Subject");
        curriculum.addTopicLabel(topicLabel_level1);
        final TopicLabel topicLabel_level2 = getTopicLabel(2, "Section");
        curriculum.addTopicLabel(topicLabel_level2);
        final TopicLabel topicLabel_level3 = getTopicLabel(3, "Parts");
        curriculum.addTopicLabel(topicLabel_level3);
        final TopicLabel topicLabel_level4 = getTopicLabel(4, "Key Issues");
        curriculum.addTopicLabel(topicLabel_level4);

        final String[] topicLabels = CurriculumTopicLabelsEnum.SUBJECT_TOPIC_KEYISSUES_EXPLANATION.toString().split("_");
        Arrays.stream(topicLabels)
                .peek(e -> System.out.println("Topic Label value: " + e))
                .collect(Collectors.toSet());

        // create curriculum change request
        final CurriculumChangeRequest changeRequest = getCurriculumChangeRequest();
        curriculum.addCurriculumSuggestions(changeRequest);

        //----Topic Labels --> Subject ---------------------------------------
        Topic subjectTopicMaths = getTopic("Maths", "Subject", true, curriculum, topicLabel_level1);

        //----Topic Labels --> Section ---------------------------------------
        Topic sectionLevel1 = getTopic("NATIONAL_5", "Section", true, curriculum, topicLabel_level2);

        Topic sectionLevel2 = getTopic("HIGHER", "Section", true, curriculum, topicLabel_level2);

        Topic sectionLevel3 = getTopic("Avd. HIGHER", "Section", true, curriculum, topicLabel_level2);

        //----Topic Labels --> Parts ---------------------------------------
        Topic partLevel1 = getTopic("Statistics", "Part", true, curriculum, topicLabel_level3);

        Topic partLevel2 = getTopic("Geometry", "Part", false, curriculum, topicLabel_level3);
        // no child topic, we can store it in DB

        Topic partLevel3 = getTopic("Algebra", "Part", false, curriculum, topicLabel_level3);


        //----Topic Labels --> Key Issue Level ---------------------------------------
        Topic KeyIssueLevel1 = getTopic("Circles", "Part", false, curriculum, topicLabel_level3);


        Topic KeyIssueLevel2 = getTopic("Pythagoras", "Part", false, curriculum, topicLabel_level3);

        //----linking between different Topic Labels  ---------------------------------------
        partLevel1.addChildren(KeyIssueLevel1);
        partLevel1.addChildren(KeyIssueLevel2);

        sectionLevel1.addChildren(partLevel1);
        sectionLevel1.addChildren(partLevel2);
        sectionLevel1.addChildren(partLevel3);

        subjectTopicMaths.addChildren(sectionLevel1);

        subjectTopicMaths.addChildren(sectionLevel2);

        subjectTopicMaths.addChildren(sectionLevel3);

        Topic subjectTopicHistory = getTopic("History", "Subject", true, curriculum, topicLabel_level1);
        Topic sectionLevel11 = getTopic("NATIONAL_5", "Section", false, curriculum, topicLabel_level2);
        subjectTopicHistory.addChildren(sectionLevel11);
        Topic subjectTopicScience = getTopic("Science", "Subject", false, curriculum, topicLabel_level1);
        curriculum.setTopics(Set.of(subjectTopicMaths, subjectTopicHistory, subjectTopicScience));

        // create block container to store different block types
        Block block = getBlock();
        block.setBlockType(BlockTypeEnum.page);
        // add page block
        final PageBlockType pageBlockType = getPageBlockType();
        block.setBlock(pageBlockType);
        // set unit block association
        subjectTopicMaths.addBlock(block);

        // save curriculum
        final Curriculum curriculum1 = curriculumService.addCurriculum(curriculum);
        //curriculumElasticSearchRepository.save(curriculum);
        // print saved curriculum in console
        CustomUtils.ObjectToJson(curriculum1);
    }

    private Topic getTopic(String title,
                           String label,
                           boolean hasChildren,
                           Curriculum curriculum,
                           TopicLabel topicLabel) {
        Topic topic = new Topic();
        topic.setTitle(title);
        topic.setLabel(label);
        topic.setHasChildren(hasChildren);
        topic.setCurriculum(curriculum);
        topic.setTopicLabel(topicLabel);
        if (topic.getTopicLabel().getSequence() == 1)
            topic.setTopicUnitTitle(title);
        return topic;
    }


    private Block getBlock() {
        Block block = new Block();
        //block.setBlockOrderOnPage(1);
        block.setArchived(false);
        block.setCreatedTime(CustomUtils.getEpochCurrentTime());
        block.setLastEditedTime(CustomUtils.getEpochCurrentTime());
        final Language language = new Language();
        language.setTitle("ENGLISH");
        //TODO:: Need to create language first and assign to block
        //block.setLanguage(language);
        return block;
    }


    private CurriculumChangeRequest getCurriculumChangeRequest() {
        final CurriculumChangeRequest changeRequest = new CurriculumChangeRequest();
        changeRequest.setChangeRequestType(ChangeRequestType.UPDATE);
        changeRequest.setChangeRequestObjectType(ChangeRequestObjectType.LEVEL);
        changeRequest.setChangeRequestStatusType(ChangeRequestStatusType.SUBMITTED);
        changeRequest.setRefId(getUniqueId());
        changeRequest.setUserRefId(getUniqueId());
        changeRequest.setChangeDescription("Change Level name NATIONAL_5 to NATIONAL_6");
        return changeRequest;
    }


    private Topic getTopic(String title) {
        Topic topic = new Topic();
        topic.setDescription(String.format("%s description", title));
        topic.setTitle(title);
        return topic;
    }


    private Country getCountry() {
        final Country country = new Country();
        country.setName("SCOTLAND");
        return country;
    }

    private String getUniqueId() {
        return UUID.randomUUID().toString();
    }


}
