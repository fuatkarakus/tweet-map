package com.advancedatabase.project.util;

import com.advancedatabase.project.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import twitter4j.*;

@Slf4j
public class CustomStatusListener implements StatusListener {

    private TweetService tweetService;

    public CustomStatusListener (TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @Override
    public void onStatus(Status status) {
        log.info("Upcoming tweet {}", status.getText());
        tweetService.saveStatus(status);
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

    }

    @Override
    public void onTrackLimitationNotice(int i) {

    }

    @Override
    public void onScrubGeo(long l, long l1) {

    }

    @Override
    public void onStallWarning(StallWarning stallWarning) {

    }

    @Override
    public void onException(Exception e) {
        log.error("Error -  {}", e);

    }
}
