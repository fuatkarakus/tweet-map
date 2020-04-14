package com.advancedatabase.project.model;

import lombok.Data;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Polygon;

@Data
@JsonComponent
public class Search {

    String key;
    Circle circle;
    Box box;
    Polygon polygon;

}
