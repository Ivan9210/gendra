package com.gendra.challenge.service;

import java.util.List;

import com.gendra.challenge.model.CitySuggestion;

/**
 * Service interface defining methods for retrieving city suggestions based on search criteria.
 */
public interface CityService {
	
	/**
     * Retrieves city suggestions based on a search query, latitude, and longitude.
     * 
     * @param query The search query.
     * @param queryLatitude The latitude of the query location.
     * @param queryLongitude The longitude of the query location.
     * @return A list of CitySuggestion objects sorted by score in descending order.
     */
    List<CitySuggestion> getSuggestions(String query, Double queryLatitude, Double queryLongitude);

}
