package com.advancedatabase.project.repository.impl;

import com.advancedatabase.project.model.Tweet;
import com.advancedatabase.project.repository.CustomTweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomTweetRepositoryImpl implements CustomTweetRepository {

    @Autowired
    MongoTemplate template;

    @Override
    public List<Tweet> findTweetsInCircle(Circle circle) {
        return template.find(new Query(Criteria.where("geoLocation").within(circle)), Tweet.class);
    }

    @Override
    public List<Tweet> findTweetsInSphere(Circle circle) {
        return template.find(new Query(Criteria.where("geoLocation").withinSphere(circle)), Tweet.class);
    }

    @Override
    public List<Tweet> findTweetsInBox(Box box) {
        return template.find(new Query(Criteria.where("geoLocation").within(box)), Tweet.class);
    }

    @Override
    public List<Tweet> findTweetsNear(Point point, Double distance) {
        return template.find(new Query(Criteria.where("geoLocation").near(point).maxDistance(distance)), Tweet.class);
    }

    @Override
    public List<Tweet> findTweetsNearSphere(Point point, Double distance) {
        return  template.find(new Query(Criteria.where("geoLocation").nearSphere(point).maxDistance(distance)), Tweet.class);
    }


}
