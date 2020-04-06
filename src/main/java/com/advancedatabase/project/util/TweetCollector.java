package com.advancedatabase.project.util;

import lombok.extern.slf4j.Slf4j;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;

@Slf4j
public class TweetCollector implements Runnable{

    private TwitterStream twitterStream;

    private TweetListener tweetListener;

    private double[][] boundary;

    private int threadNo;

    public TweetCollector(TwitterStream twitterStream, TweetListener tweetListener,
                          double[][] boundary, int threadNo) {
        this.twitterStream = twitterStream;
        this.tweetListener = tweetListener;
        this.boundary = boundary;
        this.threadNo = threadNo;
    }

    @Override
    public void run() {
        log.info("Thread - {} - started the task... ", threadNo);
        twitterStream.addListener(tweetListener)
                .filter(
                        new FilterQuery()
                        .locations(boundary)
                );
    }
}
