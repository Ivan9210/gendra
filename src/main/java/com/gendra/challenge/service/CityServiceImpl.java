package com.gendra.challenge.service;

import com.gendra.challenge.model.City;
import com.gendra.challenge.model.CitySuggestion;
import com.gendra.challenge.util.DataUtils;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation class for CityService, providing methods to manage city-related data and suggestions.
 */
@Slf4j
@Service
public class CityServiceImpl implements CityService {

	/**
	 * List to store city-related data.
	 * This list holds instances of the City class representing information about cities.
	 */
	public List<City> cityData;

	/**
	 * Constructs a new instance of the CityServiceImpl class with an empty cityData list.
	 * This constructor initializes the cityData list, which will store city-related data.
	 */
	public CityServiceImpl() {
	    // Initialize the city list
	    this.cityData = new ArrayList<>();
	}

	/**
	 * Constructs a new instance of the CityServiceImpl class with a provided cityData list.
	 * This constructor allows for initializing the cityData list with pre-existing city-related data.
	 *
	 * @param cityData The list of City objects containing city-related information.
	 */
	public CityServiceImpl(List<City> cityData) {
	    this.cityData = cityData;
	}

    /**
     * Initializes the CityServiceImpl bean after construction.
     * Loads city data from the TSV file and performs additional setup.
     */
    @PostConstruct
    public void initialize() {
        // Load city data from TSV file when starting the application
        loadCityDataFromTSV("cities_canada-usa.tsv");
        
    // Iterate over the loaded city data
    /*for (City city : cityData) {
        // Perform any additional processing or logging here
        log.info("Loaded city: " + city.getName());
    }*/
    }
    
    /**
     * Retrieves city suggestions based on a search query, latitude, and longitude.
     * 
     * @param query The search query.
     * @param queryLatitude The latitude of the query location.
     * @param queryLongitude The longitude of the query location.
     * @return A list of CitySuggestion objects sorted by score in descending order.
     */
    public List<CitySuggestion> getSuggestions(String query, Double queryLatitude, Double queryLongitude) {
        List<CitySuggestion> suggestions = new ArrayList<>();

        // Iterate through the existing city data
        for (City city : cityData) {
        	
            // Calculate the similarity score for the city based on the query and location
            double score = calculateScore(query, queryLatitude, queryLongitude, city);

            // Check if the score is greater than 0
            if (score > 0) {
                // Create a suggestion object and add it to the list
                CitySuggestion suggestion = new CitySuggestion();
                
                suggestion.setName(city.getName());
                suggestion.setLatitude(DataUtils.formatLatitude(city.getLatitude()));
                suggestion.setLongitude(DataUtils.formatLongitude(city.getLongitude()));
                suggestion.setScore(score);

                suggestions.add(suggestion);
            }
        }

        // Sort the suggestions by score in descending order
        suggestions.sort((s1, s2) -> Double.compare(s2.getScore(), s1.getScore()));

        return suggestions;
    }

    /**
     * Loads city data from a TSV file when starting the application.
     * 
     * @param fileName The name of the TSV file.
     */
    private void loadCityDataFromTSV(String fileName) {
        // Logic to load data from TSV file
        try {
            // Get TSV file resource from classpath
            Resource resource = new ClassPathResource(fileName);
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            // Read the first line (headers) and discard it
            reader.readLine();

            // Iterate over remaining lines in TSV file
            while ((line = reader.readLine()) != null) {
                
                // Split line into parts using tab delimiter
                String[] parts = line.split("\t");
                
                // Check if there are enough elements in the line before trying to create the city
                if (parts != null && parts.length >= 19) {
                    
                    // Add the created city to the city data list
                    cityData.add(createCityFromTSV(parts));
                } else {
                    // Handle line that does not have enough items
                    log.warn("Line does not have enough elements: {}", line);
                }
            }

            // Close the reader after reading all the lines
            reader.close();
        } catch (IOException e) {
            // Handle exception on error while reading TSV file
            log.error("Error reading TSV file", e);
        }
    }

    /**
     * Creates a City object from a line of the TSV file.
     * 
     * @param parts An array containing different parts of the TSV line.
     * @return The City object created from the TSV line, or null if it's the header line.
     */
    public City createCityFromTSV(String[] parts) {
        // Check if parts is null to avoid a NullPointerException later
        if (parts == null) {
            return null;
        }

        // Check if it is the first line (headers) and skip it
        if (parts[0].equalsIgnoreCase("id")) {
            return null;
        }
        
        // Create a new City object
        City city = new City();

        try {
            // Set various attributes of the City object using data from the TSV line
            city.setGeonameId(DataUtils.parseLong(parts[0]));
            city.setName(parts[1]);
            city.setAsciiName(parts[2]);
            city.setAlternateNames(parts[3]);
            city.setLatitude(DataUtils.parseDouble(parts[4]));
            city.setLongitude(DataUtils.parseDouble(parts[5]));
            city.setFeatureClass(parts[6]);
            city.setFeatureCode(parts[7]);
            city.setCountry(parts[8]);
            city.setCc2(parts[9]);
            city.setAdmin1Code(parts[10]);
            city.setAdmin2Code(parts[11]);
            city.setAdmin3Code(parts[12]);
            city.setAdmin4Code(parts[13]);
            city.setPopulation(DataUtils.parseLong(parts[14]));
            city.setElevation(DataUtils.parseInt(parts[15]));
            city.setDem(parts[16]);
            city.setTimezone(parts[17]);
            city.setModificationDate(parts[18]);
        } catch (NumberFormatException e) {
            // Handle the exception as per your needs
            e.printStackTrace();
        }

        // Return the created City object
        return city;
    }
    
    /**
     * Calculates a composite score for a City based on the matching name and location.
     * 
     * @param query The search query.
     * @param queryLatitude The latitude of the query location.
     * @param queryLongitude The longitude of the query location.
     * @param city The City object to calculate the score for.
     * @return The composite score for the City, ranging from 0 to 1.
     */
    public double calculateScore(String query, Double queryLatitude, Double queryLongitude, City city) {
        // Check if the city name contains the search query (case-insensitive)
    	if (city != null && city.getName() != null && city.getName().toLowerCase().contains(query.toLowerCase())) {
            // Calculate the score based on the similarity of the name
            double nameScore = calculateStringScore(query, city.getName());

            // Calculate the location score if latitude and longitude are provided, otherwise default to 1.0
            double locationScore = (queryLatitude != null && queryLongitude != null) ? calculateLocationScore(queryLatitude, queryLongitude, city) : 1.0;

            // Combine the name and location scores and normalize to a range of 0 to 1
            double combinedScore = (nameScore + locationScore) / 2.0;

            // Set a threshold for the combined score to determine if the City is a match
            double combinedScoreThreshold = 0.5;

            // Return the combined score if it meets the threshold, otherwise return 0
            return (combinedScore >= combinedScoreThreshold) ? Math.floor(combinedScore * 10.0) / 10.0 : 0;


        } else {
            // If the city name does not contain the search query, return 0
            return 0;
        }
    }

    /**
     * Calculates a similarity score based on the matching letters between a query and a city name.
     * 
     * @param query The search query.
     * @param cityName The city name to compare against the query.
     * @return The similarity score between 0 and 1.
     */
    private double calculateStringScore(String query, String cityName) {
        // Determine the minimum length between the query and city name
        int minLength = Math.min(query.length(), cityName.length());

        // Count the number of matching letters
        int matchingLetters = 0;

        // Iterate through each character in the minimum length
        for (int i = 0; i < minLength; i++) {
            if (query.charAt(i) == cityName.charAt(i)) {
                matchingLetters++;
            } else {
                // If a difference is found, assign a score of 0.0
                return 0.0;
            }
        }

        // Calculate the score based on the number of matching letters
        double score = (double) matchingLetters / Math.max(query.length(), cityName.length());

        return score;
    }

    /**
     * Calculates a similarity score based on the difference in latitude and longitude between a query location and a city.
     * 
     * @param queryLatitude The latitude of the query location.
     * @param queryLongitude The longitude of the query location.
     * @param city The City object to compare against the query location.
     * @return The similarity score between 0 and 1.
     */
    private double calculateLocationScore(Double queryLatitude, Double queryLongitude, City city) {
        // Calculate the similarity of latitude and longitude
        if (queryLatitude != null && queryLongitude != null) {
            // Calculate the absolute differences in latitude and longitude
            double latDiff = Math.abs(queryLatitude - city.getLatitude());
            double lonDiff = Math.abs(queryLongitude - city.getLongitude());

            // Adjust these values as needed
            double maxLatDiff = 10.0;
            double maxLonDiff = 10.0;

            // Calculate scores for latitude and longitude based on differences and maximum differences
            double latScore = (maxLatDiff - Math.min(latDiff, maxLatDiff)) / maxLatDiff;
            double lonScore = (maxLonDiff - Math.min(lonDiff, maxLonDiff)) / maxLonDiff;

            // Calculate the overall location score as the average of latitude and longitude scores
            return (latScore + lonScore) / 2.0;
        }

        // If latitude and longitude are not provided, return 1 to indicate perfect similarity
        return 1.0;
    }

}
