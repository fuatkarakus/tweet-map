package com.advancedatabase.project.util;

import com.advancedatabase.project.model.TweetFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public abstract class ConverterUtil {

    private ConverterUtil() {}

    public static final String LOCATION_JSON_FILE = "tweet-filter.json";

    public static String getResourceJson(String fileName) throws IOException {
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + fileName);
        return new String(Files.readAllBytes(file.toPath()));
    }

    public static List<TweetFilter> getStaticLocations() throws IOException {
        String locationJson = getResourceJson(LOCATION_JSON_FILE);
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

}
