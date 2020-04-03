package com.advancedatabase.project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@Configuration
public class Twitter4jConfig {

    @Bean
    Twitter getTwitter4j(){
        return new TwitterFactory().getInstance();
    }
}