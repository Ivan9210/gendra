package com.gendra.challenge.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the City class.
 */
class CityTest {

    /**
     * Test to ensure that a City object can be successfully created.
     */
    @Test
    void testCityObjectCreation() {
        // Arrange
        City city = new City();
        
        // Act (No action needed for object creation)

        // Assert
        assertNotNull(city);
    }

    /**
     * Test to ensure that the getters and setters of the City class work as expected.
     */
    @Test
    void testCityGettersAndSetters() {
        // Arrange
        City city = new City();

        // Act
        city.setGeonameId(1L);
        city.setName("Test City");
        city.setAsciiName("TestAscii");
        city.setAlternateNames("AlternateNames");
        city.setLatitude(40.7128);
        city.setLongitude(-74.0060);
        city.setFeatureClass("A");
        city.setFeatureCode("ABC");
        city.setCountry("US");
        city.setCc2("CA");
        city.setAdmin1Code("NY");
        city.setAdmin2Code("Kings County");
        city.setAdmin3Code("123");
        city.setAdmin4Code("456");
        city.setPopulation(1000000L);
        city.setElevation(50);
        city.setDem("DEM");
        city.setTimezone("America/New_York");
        city.setModificationDate("2022-01-13");

        // Assert
        assertEquals(1L, city.getGeonameId());
        assertEquals("Test City", city.getName());
        assertEquals("TestAscii", city.getAsciiName());
        assertEquals("AlternateNames", city.getAlternateNames());
        assertEquals(40.7128, city.getLatitude());
        assertEquals(-74.0060, city.getLongitude());
        assertEquals("A", city.getFeatureClass());
        assertEquals("ABC", city.getFeatureCode());
        assertEquals("US", city.getCountry());
        assertEquals("CA", city.getCc2());
        assertEquals("NY", city.getAdmin1Code());
        assertEquals("Kings County", city.getAdmin2Code());
        assertEquals("123", city.getAdmin3Code());
        assertEquals("456", city.getAdmin4Code());
        assertEquals(1000000L, city.getPopulation());
        assertEquals(50, city.getElevation());
        assertEquals("DEM", city.getDem());
        assertEquals("America/New_York", city.getTimezone());
        assertEquals("2022-01-13", city.getModificationDate());
    }

    /**
     * Test to ensure that two City objects with the same geonameId are considered equal.
     */
    @Test
    void testCityEquality() {
        // Arrange
        City city1 = new City();
        city1.setGeonameId(1L);

        City city2 = new City();
        city2.setGeonameId(1L);

        // Act (No specific action needed)

        // Assert
        assertEquals(city1, city2);
    }

    /**
     * Test to ensure that two City objects with different geonameId values are considered unequal.
     */
    @Test
    void testCityInequality() {
        // Arrange
        City city1 = new City();
        city1.setGeonameId(1L);

        City city2 = new City();
        city2.setGeonameId(2L);

        // Act (No specific action needed)

        // Assert
        assertNotEquals(city1, city2);
    }
}
