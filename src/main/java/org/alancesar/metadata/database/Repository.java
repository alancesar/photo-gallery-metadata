package org.alancesar.metadata.database;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface Repository extends MongoRepository<Entity, String> {
    Optional<Entity> findByFilename(String firstName);
}
