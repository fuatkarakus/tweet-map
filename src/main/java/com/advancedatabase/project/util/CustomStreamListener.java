package com.advancedatabase.project.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;

@Slf4j
public class CustomStreamListener implements StreamListener {

    @Override
    public void onTweet(Tweet tweet) {
        log.info("User '{}', Tweeted : {}", tweet.getUser().getName() , tweet.getText());
    }

    @Override
    public void onDelete(StreamDeleteEvent streamDeleteEvent) {

    }

    @Override
    public void onLimit(int i) {

    }

    @Override
    public void onWarning(StreamWarningEvent streamWarningEvent) {

    }
}
