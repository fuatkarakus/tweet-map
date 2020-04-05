package com.advancedatabase.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Location {

    String name;

    List<List<Double>> coordinate;

}
