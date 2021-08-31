package com.altra.apps.schema.mongodb.repository;

import com.altra.apps.schema.mongodb.model.Block;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockMongoRepository extends MongoRepository<Block, String> {
}
