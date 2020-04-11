package com.advancedatabase.project.controller;

import com.advancedatabase.project.model.Tweet;
import com.advancedatabase.project.model.request.SearchWithShape;
import com.advancedatabase.project.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import twitter4j.Location;
import twitter4j.TwitterException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class TweetController {

    @Autowired
    TweetService tweetService;

    @GetMapping(value = "/search")
    public List<Tweet> search(@RequestParam(required = false) String key) {
        return tweetService.findByKeyword(key);
    }

    @GetMapping(value = "/first")
    public List<Tweet> findAllGeoEnabledTweet() {
        return tweetService.findTweetGeoIsNotNull();
    }

    @GetMapping(value = "/searchCircle")
    public List<Tweet> findInRectangle(@RequestParam(required = false) String lat,
                                       @RequestParam(required = false) String lon,
                                       @RequestParam(required = false) String dist) {

        return tweetService.findTweetsInCircle(Double.valueOf(lat), Double.valueOf(lon), Double.valueOf(dist));
    }

//    @GetMapping(value = "/searchPolygon")
//    public List<Tweet> findInRectangle(@RequestBody ) {
//        return tweetService.findTweetsInCircle(Double.valueOf(lat), Double.valueOf(lon), Double.valueOf(dist));
//    }

    @GetMapping(value = "/locations")
    public List<Location> getLocations() throws TwitterException {
        return tweetService.getLocations();
    }
}
