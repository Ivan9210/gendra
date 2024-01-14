package com.gendra.challenge.util;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility class containing methods for parsing and formatting data types, such as Long, Double, and Integer.
 * Uses Lombok's @Slf4j annotation for logging.
 */
@Slf4j
public class DataUtils {

    /**
     * Parses a string to a Long, handling potential NumberFormatException.
     * @param value The string to parse.
     * @return Parsed Long value or null if parsing fails.
     */
    public static Long parseLong(String value) {
        try {
            return value.isEmpty() ? null : Long.parseLong(value);
        } catch (NumberFormatException e) {
            log.warn("Error parsing Long: {}", value);
            return null;
        }
    }

    /**
     * Parses a string to a Double, handling potential NumberFormatException.
     * @param value The string to parse.
     * @return Parsed Double value or null if parsing fails.
     */
    public static Double parseDouble(String value) {
        try {
            return value.isEmpty() ? null : Double.parseDouble(value);
        } catch (NumberFormatException e) {
            log.warn("Error parsing Double: {}", value);
            return null;
        }
    }

    /**
     * Parses a string to an Integer, handling potential NumberFormatException.
     * @param value The string to parse.
     * @return Parsed Integer value or null if parsing fails.
     */
    public static Integer parseInt(String value) {
        try {
            return value.isEmpty() ? null : Integer.parseInt(value);
        } catch (NumberFormatException e) {
            log.warn("Error parsing Integer: {}", value);
            return null;
        }
    }
    
    /**
     * Formats a Double latitude to a string.
     * @param latitude The latitude to format.
     * @return Formatted latitude as a string or null if the input is null.
     */
    public static String formatLatitude(Double latitude) {
        return latitude != null ? String.valueOf(latitude) : null;
    }

    /**
     * Formats a Double longitude to a string.
     * @param longitude The longitude to format.
     * @return Formatted longitude as a string or null if the input is null.
     */
    public static String formatLongitude(Double longitude) {
        return longitude != null ? String.valueOf(longitude) : null;
    }
    
}
