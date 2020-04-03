package com.advancedatabase.project.repository.impl;

import com.advancedatabase.project.repository.CustomTweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomTweetRepositoryImpl implements CustomTweetRepository {

    @Autowired
    MongoTemplate mongoTemplate;


}
