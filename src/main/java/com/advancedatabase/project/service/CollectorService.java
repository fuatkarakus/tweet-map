package com.advancedatabase.project.service;

import com.advancedatabase.project.model.TweetFilter;
import com.advancedatabase.project.util.TweetUtil;
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

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        List<TweetFilter> tweetFilters =  TweetUtil.getStaticLocations();

        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();

        executorService.submit(new TweetCollector(twitterStream, tweetService, tweetFilters));
    }
}
