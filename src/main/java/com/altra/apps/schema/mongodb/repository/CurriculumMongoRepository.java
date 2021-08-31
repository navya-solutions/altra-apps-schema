package com.altra.apps.schema.mongodb.repository;

import com.altra.apps.schema.mongodb.model.Curriculum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumMongoRepository extends MongoRepository<Curriculum, String> {
}
