package com.altra.apps.schema.rdbms.repository;

import com.altra.apps.schema.rdbms.model.Curriculum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumRepository extends CrudRepository<Curriculum, Long> {
}
