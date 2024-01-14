package com.gendra.challenge.model;

import lombok.Data;

/**
 * Model class representing city suggestions with name, latitude, longitude, and score.
 * Uses Lombok's @Data annotation to automatically generate getters, setters, toString, and other methods.
 */
@Data
public class CitySuggestion {

    // Attributes
    private String name;
    private String latitude;
    private String longitude;
    private double score;

}
