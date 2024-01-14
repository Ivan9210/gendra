package com.gendra.challenge.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataUtilsTest {

    @Test
    void parseLong_ValidInput_ShouldParseSuccessfully() {
        String validInput = "123";
        long result = DataUtils.parseLong(validInput);
        assertEquals(123L, result);
    }

    @Test
    void parseInt_ValidInput_ShouldParseSuccessfully() {
        String validInput = "456";
        int result = DataUtils.parseInt(validInput);
        assertEquals(456, result);
    }

    @Test
    void parseDouble_ValidInput_ShouldParseSuccessfully() {
        String validInput = "3.14";
        double result = DataUtils.parseDouble(validInput);
        assertEquals(3.14, result, 0.0001); // Specify delta for double comparison
    }

}
