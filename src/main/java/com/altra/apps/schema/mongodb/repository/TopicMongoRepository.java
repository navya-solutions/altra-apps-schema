package com.altra.apps.schema.mongodb.repository;

import com.altra.apps.schema.mongodb.model.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicMongoRepository extends MongoRepository<Topic, String> {
}
