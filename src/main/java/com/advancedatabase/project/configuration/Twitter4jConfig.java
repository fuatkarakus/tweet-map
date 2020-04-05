package com.advancedatabase.project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.*;

@Configuration
public class Twitter4jConfig {

    @Bean
    Twitter getTwitter4j(){
        return new TwitterFactory().getInstance();
    }

    @Bean
    TwitterStream getTwitterStream4j() {
        return new TwitterStreamFactory().getInstance();
    }

    @Bean
    AsyncTwitter getAsyncTwitter4j() {
        return new AsyncTwitterFactory().getInstance();
    }

}
