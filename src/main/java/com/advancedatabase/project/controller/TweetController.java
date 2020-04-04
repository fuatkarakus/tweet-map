package com.advancedatabase.project.controller;

import com.advancedatabase.project.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.Location;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class TweetController {

    @Autowired
    TweetService tweetService;

    @GetMapping(value = "/getTrends")
    public Map<String, List<Tweet>> getTrendsByCountry(@RequestParam(defaultValue = "ww") String id,
                                                       @RequestParam(defaultValue = "100") String count) {
        return tweetService.getTrendTweetsByCountry(id, count);
    }

    @GetMapping(value = "/getTweetsFromIstanbul")
    public List<Tweet> getTweets () {
        return tweetService.getTweetByLocation();
    }

    @GetMapping(value = "/getTweets4j")
    public List<Status> getTweets4j () throws TwitterException {
        return tweetService.getTweets();
    }

    @GetMapping(value = "/getLocations")
    public List<Location> getLocations() throws TwitterException {
        return tweetService.getLocations();
    }
}
