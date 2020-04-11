package com.advancedatabase.project.repository;

import com.advancedatabase.project.model.Tweet;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Point;

import java.util.List;

public interface CustomTweetRepository {

    List<Tweet> findTweetsInCircle(Circle circle);

    List<Tweet> findTweetsInSphere(Circle circle);

    List<Tweet> findTweetsInBox(Box box);

    List<Tweet> findTweetsNear(Point point, Double distance);

    List<Tweet> findTweetsNearSphere(Point point, Double distance);

}
