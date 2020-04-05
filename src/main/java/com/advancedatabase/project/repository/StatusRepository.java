package com.advancedatabase.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import twitter4j.Status;

public interface StatusRepository extends MongoRepository<Status, Long> {
}
