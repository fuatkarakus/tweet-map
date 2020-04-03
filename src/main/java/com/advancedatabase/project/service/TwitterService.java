package com.advancedatabase.project.service;

import com.advancedatabase.project.repository.TweetRepository;
import com.advancedatabase.project.util.CountryWOEID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.*;
import org.springframework.social.twitter.api.Trend;
import org.springframework.social.twitter.api.Trends;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TwitterService {

    @Autowired
    private Twitter twitter;

    @Autowired
    twitter4j.Twitter twitter4j;

    @Autowired
    private TweetRepository tweetRepository;

    public List<Tweet> getTweetByLocation () {
        SearchResults results;
        results = twitter.searchOperations().search(
                new SearchParameters("")
                        //istanbul geocode
                        .geoCode(new GeoCode(41.01224, 28.976018, 100, GeoCode.Unit.KILOMETER))
                        .resultType(SearchParameters.ResultType.RECENT)
                        .count(10)
                        .includeEntities(false));

        return results.getTweets();
    }

    public List<Status> getTweets() throws TwitterException {

        Query query = new Query()
                .geoCode(new GeoLocation(41.01224, 28.976018),
                10, Query.Unit.km)
                .count(50); //You can also set the number of tweets to return per page, up to a max of 100

        return twitter4j.search(query).getTweets();

    }

    public Map<String, List<Tweet>> getTrendTweetsByCountry(String id, String count){

        Trends trends = twitter.searchOperations()
                .getLocalTrends(CountryWOEID.valueOf(id.toUpperCase()).getWoeid());

        log.debug("returned all trends: "+trends.getTrends().size());

        HashMap<String, List<Tweet>> trendMap = new HashMap<>();
        for (Trend trend : trends.getTrends()) {
            log.debug("search trend:"+trend.getName());
            SearchResults results = twitter.searchOperations().search(
                    new SearchParameters(trend.getName())
                            .count(Integer.parseInt(count)));
            log.debug("search result:"+results.getTweets().size());
            trendMap.put(trend.getName(), results.getTweets());
            for (Tweet tweet : results.getTweets()) {
                    tweetRepository.save(tweet);
            }
        }

        return trendMap;
    }

}
