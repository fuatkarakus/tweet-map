package com.advancedatabase.project.service;

import com.advancedatabase.project.model.Location;
import com.advancedatabase.project.util.ConverterUtil;
import com.advancedatabase.project.util.CustomStatusListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class TweetCollector{

    TweetService tweetService;

    @Autowired
    public TweetCollector(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    // TODO burada her lokasyon icin yeni bir thread baslatilacak
    @EventListener(ApplicationReadyEvent.class)
    public void runCollectors() throws IOException {
        log.info(" Starting listeners... ");
        List<Location> locations =  ConverterUtil.getStaticLocations();
        CustomStatusListener listener = new CustomStatusListener(tweetService);
        for (Location location: locations ){
            TwitterStream twitterStream = new  TwitterStreamFactory().getInstance();

            twitterStream.addListener(listener)
                    .filter(new FilterQuery()
                            .locations(ConverterUtil.getMatrixOfLocation(location)));
        }
    }
}
