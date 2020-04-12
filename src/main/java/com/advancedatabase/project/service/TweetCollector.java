package com.advancedatabase.project.service;

import com.advancedatabase.project.model.TweetFilter;
import com.advancedatabase.project.util.TweetUtil;
import lombok.extern.slf4j.Slf4j;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;

import java.util.List;

@Slf4j
public class TweetCollector implements Runnable{

    private TwitterStream twitterStream;

    TweetService tweetService;

    private List<TweetFilter> tweetFilters;

    public TweetCollector(TwitterStream twitterStream, TweetService tweetService, List<TweetFilter> tweetFilters) {
        this.twitterStream = twitterStream;
        this.tweetService = tweetService;
        this.tweetFilters = tweetFilters;
    }

    @Override
    public void run() {

        twitterStream.addListener(new TweetListener(tweetService));

        for (TweetFilter tweetFilter : tweetFilters) {

            FilterQuery filterQuery = new FilterQuery(TweetUtil.getTracksAsArray(tweetFilter))
                    .locations(TweetUtil.getMatrixOfLocation(tweetFilter));

            twitterStream.filter(filterQuery);
        }

    }
}
