package com.advancedatabase.project.util;

import com.advancedatabase.project.model.Location;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public abstract class ConverterUtil {

    private ConverterUtil() {}

    public static final String LOCATION_JSON_FILE = "location.json";

    public static String getResourceJson(String fileName) throws IOException {
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + fileName);
        return new String(Files.readAllBytes(file.toPath()));
    }

    public static List<Location> getStaticLocations() throws IOException {
        String locationJson = getResourceJson(LOCATION_JSON_FILE);
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(locationJson, Location[].class));
    }

    public static double[][] getMatrixOfLocation (Location location) {
        double[][] array;

        array = location.getCoordinate()
                .stream()
                .map(l -> l.stream()
                        .mapToDouble(Double::doubleValue)
                        .toArray())
                .toArray(double[][]::new);

        return array;
    }

}