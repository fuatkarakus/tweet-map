package com.advancedatabase.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
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

    @TextIndexed
    String text;

    @GeoSpatialIndexed
    GeoLocation geoLocation;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date createdAt;

    @Field("user.profileImageUrl")
    String profileImageUrl;

    @Field("place.fullName")
    String place;

}
