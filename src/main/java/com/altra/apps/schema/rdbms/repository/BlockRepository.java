package com.altra.apps.schema.rdbms.repository;

import com.altra.apps.schema.rdbms.model.Block;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends CrudRepository<Block, Long> {
}
