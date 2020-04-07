package com.advancedatabase.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetFilter {

    String name;

    List<List<Double>> coordinate;

    List<String> track;

}
