package com.advancedatabase.project.repository;

import com.advancedatabase.project.model.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TweetRepository extends MongoRepository<Tweet, Long>, CustomTweetRepository {

    List<Tweet> findTweetsByTextLikeAndGeoLocationIsNotNull(String key);

    List<Tweet> findByGeoLocationNotNull();

    Page<Tweet> findTweetsByGeoLocationIsNotNullOrderByCreatedAtDesc(Pageable pageable);

    List<Tweet> findByGeoLocationWithinAndTextLike(Circle circle, String key);

    List<Tweet> findByGeoLocationWithinAndTextLike(Box box, String key);

    List<Tweet> findByGeoLocationWithinAndTextLike(Polygon polygon, String key);

    List<Tweet> findByGeoLocationWithin(Circle circle);

    List<Tweet> findByGeoLocationWithin(Circle circle, TextCriteria criteria);

    List<Tweet> findByGeoLocationWithin(Box box);

    List<Tweet> findByGeoLocationWithin(Polygon polygon);

    List<Tweet> findByGeoLocationNear(Point point, Distance max);

}
