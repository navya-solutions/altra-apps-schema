package com.altra.apps.schema.rdbms.repository;

import com.altra.apps.schema.rdbms.model.Block;
import com.altra.apps.schema.rdbms.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
