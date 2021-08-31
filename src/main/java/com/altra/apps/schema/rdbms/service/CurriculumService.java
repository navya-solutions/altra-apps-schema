package com.altra.apps.schema.rdbms.service;

import com.altra.apps.schema.rdbms.model.Curriculum;
import com.altra.apps.schema.rdbms.repository.CurriculumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CurriculumService {
    private final CurriculumRepository curriculumRepository;

    public Set<Curriculum> getAll() {
        return StreamSupport.stream(curriculumRepository.findAll().spliterator(), true)
                .collect(Collectors.toSet());
    }

    public Curriculum addCurriculum(Curriculum curriculum) {
        return curriculumRepository.save(curriculum);
    }
}
