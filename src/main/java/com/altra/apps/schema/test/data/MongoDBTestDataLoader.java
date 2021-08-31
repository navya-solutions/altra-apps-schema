package com.altra.apps.schema.test.data;

import com.altra.apps.schema.common.BlockTypeEnum;
import com.altra.apps.schema.common.CustomUtils;
import com.altra.apps.schema.mongodb.model.Block;
import com.altra.apps.schema.mongodb.model.Curriculum;
import com.altra.apps.schema.mongodb.model.Topic;
import com.altra.apps.schema.mongodb.repository.BlockMongoRepository;
import com.altra.apps.schema.mongodb.repository.CurriculumMongoRepository;
import com.altra.apps.schema.mongodb.repository.TopicMongoRepository;
import com.altra.apps.schema.mongodb.type.LanguageType;
import com.altra.apps.schema.type.PageBlockType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class MongoDBTestDataLoader implements TestDataLoader {


    private final CurriculumMongoRepository curriculumMongoDBRepository;
    private final TopicMongoRepository topicMongoRepository;
    private final BlockMongoRepository blockMongoRepository;

    @Override
    public void testDataLoader() {
        generateMongoDBTestData();
    }


    private void generateMongoDBTestData() {
        Curriculum curriculum = new Curriculum();
        curriculum.setHasPublicAccess(true);
        curriculum.setId(CustomUtils.getUniqueId());
        curriculum = curriculumMongoDBRepository.save(curriculum);

        //----Topic Labels --> Subject ---------------------------------------
        Topic subjectTopicMaths = getTopic("Maths", "Subject", true, curriculum);


        //----Topic Labels --> Section ---------------------------------------
        Topic sectionLevel1 = getTopic("NATIONAL_5", "Section", true, curriculum);

        Topic sectionLevel2 = getTopic("HIGHER", "Section", true, curriculum);

        Topic sectionLevel3 = getTopic("Avd. HIGHER", "Section", true, curriculum);


        //----Topic Labels --> Parts ---------------------------------------
        Topic partLevel1 = getTopic("Statistics", "Part", true, curriculum);

        Topic partLevel2 = getTopic("Geometry", "Part", false, curriculum);
        // no child topic, we can store it in DB
        partLevel2 = topicMongoRepository.save(partLevel2);

        Topic partLevel3 = getTopic("Algebra", "Part", false, curriculum);
        // no child topic, we can store it in DB
        partLevel3 = topicMongoRepository.save(partLevel3);


        //----Topic Labels --> Key Issue Level ---------------------------------------
        Topic KeyIssueLevel1 = getTopic("Circles", "Part", false, curriculum);
        // store child topic ref in DB first and then add to the parent topic
        KeyIssueLevel1 = topicMongoRepository.save(KeyIssueLevel1);

        Topic KeyIssueLevel2 = getTopic("Pythagoras", "Part", false, curriculum);
        KeyIssueLevel2 = topicMongoRepository.save(KeyIssueLevel2);

        //----linking between different Topic Labels  ---------------------------------------
        partLevel1.addChildTopic(KeyIssueLevel1);
        partLevel1.addChildTopic(KeyIssueLevel2);

        partLevel1 = topicMongoRepository.save(partLevel1);

        sectionLevel1.addChildTopic(partLevel1);
        sectionLevel1.addChildTopic(partLevel2);
        sectionLevel1.addChildTopic(partLevel3);

        sectionLevel1 = topicMongoRepository.save(sectionLevel1);

        subjectTopicMaths.addChildTopic(sectionLevel1);
        sectionLevel2 = topicMongoRepository.save(sectionLevel2);

        subjectTopicMaths.addChildTopic(sectionLevel2);
        sectionLevel3 = topicMongoRepository.save(sectionLevel3);

        subjectTopicMaths.addChildTopic(sectionLevel3);

        Topic subjectTopicHistory = getTopic("History", "Subject", false, curriculum);
        Topic subjectTopicScience = getTopic("Science", "Subject", false, curriculum);
        curriculum.setTopics(Set.of(subjectTopicMaths, subjectTopicHistory, subjectTopicScience));

        subjectTopicMaths = topicMongoRepository.save(subjectTopicMaths);
        subjectTopicHistory = topicMongoRepository.save(subjectTopicHistory);
        subjectTopicScience = topicMongoRepository.save(subjectTopicScience);

        curriculum = curriculumMongoDBRepository.save(curriculum);


        Block block = getBlock();
        // create block container to store different block types
        block.setBlockType(BlockTypeEnum.page);
        // add page block
        final PageBlockType pageBlockType = getPageBlockType();
        block.setBlock(pageBlockType);
        block.setTopic(subjectTopicMaths);
        // set unit block association
        subjectTopicMaths.setBlocks(List.of(block));

        blockMongoRepository.save(block);

        subjectTopicMaths = topicMongoRepository.save(subjectTopicMaths);
    }

    private Block getBlock() {
        Block block = new Block();
        //block.setBlockOrderOnPage(1);
        block.setArchived(false);
        block.setId(CustomUtils.getUniqueId());
        block.setCreatedTime(CustomUtils.getEpochCurrentTime());
        block.setLastEditedTime(CustomUtils.getEpochCurrentTime());
        final LanguageType language = new LanguageType();
        language.setTitle("ENGLISH");
        block.setLanguages(Set.of(language));
        return block;
    }

    private Topic getTopic(String title,
                           String label,
                           boolean hasChildren,
                           com.altra.apps.schema.mongodb.model.Curriculum curriculum) {
        Topic topic = new Topic();
        topic.setId(CustomUtils.getUniqueId());
        topic.setTitle(title);
        topic.setLabel(label);
        topic.setHasChildren(hasChildren);
        topic.setCurriculum(curriculum);
        return topic;
    }


}
