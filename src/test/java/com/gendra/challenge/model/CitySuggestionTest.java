package com.gendra.challenge.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CitySuggestion class.
 */
class CitySuggestionTest {

    /**
     * Test to ensure that a CitySuggestion object can be successfully created.
     */
    @Test
    void testCitySuggestionObjectCreation() {
        // Arrange
        CitySuggestion citySuggestion = new CitySuggestion();
        
        // Act (No action needed for object creation)

        // Assert
        assertNotNull(citySuggestion);
    }

    /**
     * Test to ensure that the getters and setters of the CitySuggestion class work as expected.
     */
    @Test
    void testCitySuggestionGettersAndSetters() {
        // Arrange
        CitySuggestion citySuggestion = new CitySuggestion();

        // Act
        citySuggestion.setName("Test City");
        citySuggestion.setLatitude("40.7128");
        citySuggestion.setLongitude("-74.0060");
        citySuggestion.setScore(0.75);

        // Assert
        assertEquals("Test City", citySuggestion.getName());
        assertEquals("40.7128", citySuggestion.getLatitude());
        assertEquals("-74.0060", citySuggestion.getLongitude());
        assertEquals(0.75, citySuggestion.getScore());
    }

    /**
     * Test to ensure that two CitySuggestion objects with the same name are considered equal.
     */
    @Test
    void testCitySuggestionEquality() {
        // Arrange
        CitySuggestion suggestion1 = new CitySuggestion();
        suggestion1.setName("Test City");

        CitySuggestion suggestion2 = new CitySuggestion();
        suggestion2.setName("Test City");

        // Act (No specific action needed)

        // Assert
        assertEquals(suggestion1, suggestion2);
    }

    /**
     * Test to ensure that two CitySuggestion objects with different names are considered unequal.
     */
    @Test
    void testCitySuggestionInequality() {
        // Arrange
        CitySuggestion suggestion1 = new CitySuggestion();
        suggestion1.setName("Test City 1");

        CitySuggestion suggestion2 = new CitySuggestion();
        suggestion2.setName("Test City 2");

        // Act (No specific action needed)

        // Assert
        assertNotEquals(suggestion1, suggestion2);
    }
}
