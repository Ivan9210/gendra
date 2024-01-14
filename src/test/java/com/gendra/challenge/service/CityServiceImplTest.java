package com.gendra.challenge.service;

import com.gendra.challenge.model.City;
import com.gendra.challenge.model.CitySuggestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CityServiceImplTest {

    @Mock
    private ResourceLoader resourceLoader;

    @InjectMocks
    private CityServiceImpl cityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cityService.cityData = new ArrayList<>();
    }

    @Test
    void testGetSuggestions() {
        // Crear instancias de City para simular datos de la ciudad
        City city1 = new City();
        
        city1.setName("Toronto");
        city1.setLatitude(43.70011);
        city1.setLongitude(-79.4163);

        // Crear una lista de ciudades de prueba
        List<City> cityData = Arrays.asList(city1);

        // Crear una instancia de la clase que contiene el método getSuggestions
        CityServiceImpl cityService = new CityServiceImpl(cityData);

        // Llamar al método getSuggestions con datos de prueba
        List<CitySuggestion> suggestions = cityService.getSuggestions("Toronto", 43.70011, -79.4163);

        // Verificar que la lista de sugerencias no esté vacía
        assertFalse(suggestions.isEmpty());

        // Verificar que las sugerencias estén ordenadas por puntaje descendente
        for (int i = 1; i < suggestions.size(); i++) {
            assertTrue(suggestions.get(i - 1).getScore() >= suggestions.get(i).getScore());
        }
    }

    @Test
    void testInitialize() throws IOException {
    	
    	// Given
        Resource mockResource = mockResource("cities_canada-usa.tsv");
        when(resourceLoader.getResource(anyString())).thenReturn(mockResource);

        // When
        cityService.initialize();
        
    }

    // Helper method to create a mocked Resource for testing
    private Resource mockResource(String fileName) throws IOException {
    	
        Resource resource = mock(Resource.class);
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        when(resource.getInputStream()).thenReturn(inputStream);
        return resource;
        
    }

}
