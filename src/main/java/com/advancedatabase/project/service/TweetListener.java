package com.advancedatabase.project.service;

import lombok.extern.slf4j.Slf4j;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

@Slf4j
public class TweetListener implements StatusListener {

    private TweetService tweetService;

    public TweetListener(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @Override
    public void onStatus(Status status) {
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
