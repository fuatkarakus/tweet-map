package com.advancedatabase.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import twitter4j.Location;

public interface LocationRepository extends MongoRepository<Location, Integer> {
}
