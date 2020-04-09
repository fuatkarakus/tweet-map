package com.advancedatabase.project.controller;

import com.advancedatabase.project.model.Tweet;
import com.advancedatabase.project.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.Location;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class TweetController {

    @Autowired
    TweetService tweetService;

    @GetMapping(value = "/search")
    public List<Tweet> search(@PathVariable String key) {
        return tweetService.findByKeyword(key);
    }

    @GetMapping(value = "/first")
    public List<Tweet> findAllGeoEnabledTweet() {
        return tweetService.findTweetGeoIsNotNull();
    }

    @GetMapping(value = "/update")
    public List<Tweet> update() {
        return tweetService.findLatestTweet();
    }

    @GetMapping(value = "/tweets4j")
    public List<Status> getTweets4j () throws TwitterException {
        return tweetService.getTweets();
    }

    @GetMapping(value = "/locations")
    public List<Location> getLocations() throws TwitterException {
        return tweetService.getLocations();
    }
}
