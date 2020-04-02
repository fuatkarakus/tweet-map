package com.advancedatabase.project.service;

import com.advancedatabase.project.util.CountryWOEID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TwitterService {

    @Autowired
    private Twitter twitter;

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
        }

        return trendMap;
    }

}
