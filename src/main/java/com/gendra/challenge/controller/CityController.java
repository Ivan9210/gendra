package com.gendra.challenge.controller;

import com.gendra.challenge.model.CitySuggestion;
import com.gendra.challenge.service.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Controller class responsible for handling HTTP requests related to city suggestions.
 */
@RestController
@RequestMapping("/api/gendra")
public class CityController {

    // Service responsible for business logic related to city data
    private final CityService cityService;

    /**
     * Constructor for CityController.
     *
     * @param cityService The injected CityService instance to handle city-related operations.
     */
    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Handles HTTP GET requests for city suggestions based on a search query and optional location coordinates.
     *
     * @param q         The search query parameter.
     * @param latitude  The optional latitude parameter for location-based suggestions.
     * @param longitude The optional longitude parameter for location-based suggestions.
     * @return A ResponseEntity containing a map with a list of city suggestions.
     */
    @GetMapping("/suggestions")
    @ResponseBody
    public ResponseEntity<Map<String, List<CitySuggestion>>> getSuggestions(
            @RequestParam String q,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude) {
    	
    	// Retrieve city suggestions from the CityService
    	List<CitySuggestion> suggestions = cityService.getSuggestions(q, latitude, longitude);

        // Returns a ResponseEntity directly with a map containing the list of suggestions
        return ResponseEntity.ok(Map.of("suggestions", suggestions));
    }
}
