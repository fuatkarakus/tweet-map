package com.advancedatabase.project.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import twitter4j.GeoLocation;

import java.util.Date;

@Data
@Document(collection = "status")
public class Tweet {

    @Id
    @Field("_id")
    Long id;

    @Field("user.screenName")
    String name;

    @Indexed
    String text;

    @Indexed
    GeoLocation geoLocation;

    Date createdAt;

    @Field("user.profileImageUrl")
    String profileImageUrl;

}
