package com.gendra.challenge.model;

import lombok.Data;

/**
 * Model class representing city information.
 * Uses Lombok's @Data annotation to automatically generate getters, setters, toString, and other methods.
 */
@Data
public class City {

    // Attributes
    private Long geonameId;
    private String name;
    private String asciiName;
    private String alternateNames;
    private Double latitude;
    private Double longitude;
    private String featureClass;
    private String featureCode;
    private String country;
    private String cc2;
    private String admin1Code;
    private String admin2Code;
    private String admin3Code;
    private String admin4Code;
    private Long population;
    private Integer elevation;
    private String dem;
    private String timezone;
    private String modificationDate;

}
