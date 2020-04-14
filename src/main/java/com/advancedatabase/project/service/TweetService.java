package com.advancedatabase.project.service;

import com.advancedatabase.project.model.Tweet;
import com.advancedatabase.project.repository.StatusRepository;
import com.advancedatabase.project.repository.TweetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.List;

@Slf4j
@Service
public class TweetService {

    private Twitter twitter4j;

    private StatusRepository statusRepository;

    private TweetRepository tweetRepository;

    @Autowired
    TweetService(Twitter twitter4j, StatusRepository statusRepository, TweetRepository tweetRepository) {
        this.twitter4j = twitter4j;
        this.statusRepository = statusRepository;
        this.tweetRepository = tweetRepository;
    }

    public void saveStatus(Status status) {
        statusRepository.save(status);
    }

    public List<Tweet> findTweetsInCircle(Circle circle) {
        return tweetRepository.findByGeoLocationWithin(circle);
    }

    public List<Tweet> findTweetsInPolygon(Polygon points) {
        return tweetRepository.findByGeoLocationWithin(points);
    }

    public List<Tweet> findTweetsInBox(Box box) {
        return tweetRepository.findByGeoLocationWithin(box);
    }

    public List<Tweet> findTweetsInCircleAndKey(Circle circle, String key) {
        return tweetRepository.findByGeoLocationWithinAndTextLike(circle,key);
    }

    public List<Tweet> findTweetsInCircleWithTextCriteria(Circle circle, String key) {
        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching(key);
        return tweetRepository.findByGeoLocationWithin(circle,textCriteria);
    }

    public List<Tweet> findTweetsInPolygonAndKey(Polygon points, String key) {
        return tweetRepository.findByGeoLocationWithinAndTextLike(points,key);
    }

    public List<Tweet> findTweetsInBoxAndKey(Box box, String key) {
        return tweetRepository.findByGeoLocationWithinAndTextLike(box, key);
    }

    public List<Tweet> findByKeyword(String key) {
        return tweetRepository.findTweetsByTextLikeAndGeoLocationIsNotNull(key);
    }

    public List<Tweet> findLatestTweet() {
        return tweetRepository.findTweetsByGeoLocationIsNotNullOrderByCreatedAtDesc(PageRequest.of(0,10)).getContent();
    }

    @Cacheable("TweetGeoNotNull")
    public List<Tweet> findTweetGeoNotNull() {
        List<Tweet> tweets = tweetRepository.findByGeoLocationNotNull();
        log.info("TWEET SIZE -- {}", tweets.size());
        return tweets;
    }

    public List<Location> getLocations() throws TwitterException {
        return  twitter4j.getAvailableTrends();
    }

    public List<Status> getTweets() throws TwitterException {

        return twitter4j.search(new Query()
                                    .geoCode(new GeoLocation(41.01224, 28.976018),
                                    10, Query.Unit.km)
                                    .count(50))
                //You can also set the number of tweets to return per page, up to a max of 100
                        .getTweets();

    }

}
