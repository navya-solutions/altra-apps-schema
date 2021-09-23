package com.altra.apps.schema.rdbms.service;

import com.altra.apps.schema.rdbms.model.Block;
import com.altra.apps.schema.rdbms.model.Curriculum;
import com.altra.apps.schema.rdbms.repository.BlockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class BlockService {
    private final BlockRepository blockRepository;

    public Set<Block> getAll() {
        return StreamSupport.stream(blockRepository.findAll().spliterator(), true)
                .collect(Collectors.toSet());
    }

    /*public Block addBlock(Block block) {
    }*/

    public void getAllCurriculumWithTopicHierarchical() {
        //curriculumRepository.findAll();

    }
}
