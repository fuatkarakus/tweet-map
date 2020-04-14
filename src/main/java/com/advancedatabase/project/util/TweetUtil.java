package com.advancedatabase.project.util;

import com.advancedatabase.project.model.Search;
import com.advancedatabase.project.model.Tweet;
import com.advancedatabase.project.model.TweetFilter;
import com.advancedatabase.project.service.TweetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class TweetUtil {

    private TweetUtil() {}

    public static final String FILTER_JSON = "tweet-filter.json";

    public static String getResourceJson(String fileName) throws IOException {
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + fileName);
        return new String(Files.readAllBytes(file.toPath()));
    }

    public static List<TweetFilter> getStaticLocations() throws IOException {
        String locationJson = getResourceJson(FILTER_JSON);
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(locationJson, TweetFilter[].class));
    }

    public static double[][] getMatrixOfLocation (TweetFilter tweetFilter) {
        double[][] array;

        array = tweetFilter.getCoordinate()
                .stream()
                .map(l -> l.stream()
                        .mapToDouble(Double::doubleValue)
                        .toArray())
                .toArray(double[][]::new);

        return array;
    }

    public static String[] getTracksAsArray(TweetFilter tweetFilter){
        return tweetFilter.getTrack()
                .toArray(String[]::new);
    }

    public static List<Tweet> getTweetsBySearch(TweetService tweetService, Search search) {
        List<Tweet> tweets = new ArrayList<>();
        if( search.getKey() == null || search.getKey().equals("") ) {
            if (search.getCircle() != null ) {
                tweets= tweetService.findTweetsInCircle(search.getCircle());
            } else if (search.getBox() != null ) {
                tweets= tweetService.findTweetsInBox(search.getBox());
            } else if (search.getPolygon() != null ) {
                tweets= tweetService.findTweetsInPolygon(search.getPolygon());
            } else {
                tweets = tweetService.findTweetGeoNotNull();
            }
        } else if ( search.getKey() != null ){
            if ( search.getCircle() != null ) {
                tweets= tweetService.findTweetsInCircleWithTextCriteria(search.getCircle(), search.getKey());
            } else if (search.getBox() != null ) {
                tweets= tweetService.findTweetsInBoxAndKey(search.getBox(), search.getKey());
            } else if (search.getPolygon() != null ) {
                tweets= tweetService.findTweetsInPolygonAndKey(search.getPolygon(), search.getKey());
            }
        }
        return tweets;
    }

}
