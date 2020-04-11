package com.advancedatabase.project.model.request;

import lombok.Data;
import org.springframework.data.geo.*;

@Data
public class SearchWithShape {
    String key;
    Point point;
    Circle circle;
    Box box;
    Polygon polygon;
    Distance distance;
}
