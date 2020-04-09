package com.advancedatabase.project.repository;

import com.advancedatabase.project.model.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TweetRepository extends MongoRepository<Tweet, Long>, CustomTweetRepository {

    List<Tweet> findTweetsByTextLikeAndGeoLocationIsNotNull(String key);

    List<Tweet> findByGeoLocationIsNotNull();

    List<Tweet> findByGeoLocationNotNull();

    Page<Tweet> findTweetsByGeoLocationIsNotNullOrderByCreatedAtDesc(Pageable pageable);

}
