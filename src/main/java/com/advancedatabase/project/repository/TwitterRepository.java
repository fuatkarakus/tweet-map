package com.advancedatabase.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.social.twitter.api.Tweet;

public interface TwitterRepository extends MongoRepository<Tweet, Long> {

}
