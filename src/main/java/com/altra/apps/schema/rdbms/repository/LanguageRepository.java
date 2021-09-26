package com.altra.apps.schema.rdbms.repository;

import com.altra.apps.schema.rdbms.model.Block;
import com.altra.apps.schema.rdbms.model.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Long> {
}
