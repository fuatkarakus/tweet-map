package com.advancedatabase.project.service;

import com.advancedatabase.project.model.Location;
import com.advancedatabase.project.util.ConverterUtil;
import com.advancedatabase.project.util.TweetCollector;
import com.advancedatabase.project.util.TweetListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class CollectorService {

    TweetService tweetService;

    @Autowired
    public CollectorService(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runCollectors() throws IOException {
        log.info("Starting collectors... ");

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        List<Location> locations =  ConverterUtil.getStaticLocations();

        int idx = 0;

        for (Location location: locations){

            TweetListener listener = new TweetListener(tweetService);

            TwitterStream twitterStream = new TwitterStreamFactory().getInstance();

            log.info("-- {} -- city starting...", location.getName());
            executorService.submit(new TweetCollector(twitterStream, listener,
                    ConverterUtil.getMatrixOfLocation(location), idx));

            idx++;

        }
    }
}
