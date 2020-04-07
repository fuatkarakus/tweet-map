package com.advancedatabase.project.util;

import com.advancedatabase.project.model.TweetFilter;
import com.advancedatabase.project.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import twitter4j.*;

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

            FilterQuery filterQuery = new FilterQuery(ConverterUtil.getTracksAsArray(tweetFilter))
                    .locations(ConverterUtil.getMatrixOfLocation(tweetFilter));

            twitterStream .filter(filterQuery);
        }

    }
}
