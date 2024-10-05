package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.model.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.CountryToSave;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import ar.edu.utn.frc.tup.lciii.service.serviceImp.CountryServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CountryServiceTest {
    @MockBean
    private CountryRepository countryRepository;

    @MockBean
    private RestTemplate restTemplate;
    @SpyBean
    private CountryService  service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCountries() {
        List<Map<String, Object>> response = new ArrayList<>();

        Map<String, Object> countryData1 = new HashMap<>();
        countryData1.put("name", Map.of("common", "Country 1"));
        countryData1.put("population", 1000000L);
        countryData1.put("area", 100000.0);
        countryData1.put("region", "Region 1");
        countryData1.put("languages", Map.of("es", "Spanish"));
        countryData1.put("cca3", "COTRY1");
        countryData1.put("borders", List.of("BORDER1", "BORDER2"));
        response.add(countryData1);

        Map<String, Object> countryData2 = new HashMap<>();
        countryData2.put("name", Map.of("common", "Country 2"));
        countryData2.put("population", 2000000L);
        countryData2.put("area", 200000.0);
        countryData2.put("region", "Region 2");
        countryData2.put("languages", Map.of("en", "English"));
        countryData2.put("cca3", "COTRY2");
        countryData2.put("borders", List.of("BORDER3", "BORDER4"));
        response.add(countryData2);

        when(restTemplate.getForObject("https://restcountries.com/v3.1/all", List.class))
                .thenReturn(response);

        List<CountryDTO> countries = service.getAllCountries(null, null);

        assertEquals(2, countries.size());
        assertEquals("COTRY1", countries.get(0).getCode());
        assertEquals("Country 1", countries.get(0).getName());
        assertEquals("COTRY2", countries.get(1).getCode());
        assertEquals("Country 2", countries.get(1).getName());
    }

    @Test
    void getAllCountriesByCode() {
        List<Map<String, Object>> response = new ArrayList<>();

        Map<String, Object> countryData1 = new HashMap<>();
        countryData1.put("name", Map.of("common", "Country 1"));
        countryData1.put("population", 1000000L);
        countryData1.put("area", 100000.0);
        countryData1.put("region", "Region 1");
        countryData1.put("languages", Map.of("es", "Spanish"));
        countryData1.put("cca3", "COUNTRY1");
        countryData1.put("borders", List.of("BORDER1", "BORDER2"));
        response.add(countryData1);

        Map<String, Object> countryData2 = new HashMap<>();
        countryData2.put("name", Map.of("common", "Country 2"));
        countryData2.put("population", 2000000L);
        countryData2.put("area", 200000.0);
        countryData2.put("region", "Region 2");
        countryData2.put("languages", Map.of("en", "English"));
        countryData2.put("cca3", "COUNTRY2");
        countryData2.put("borders", List.of("BORDER3", "BORDER4"));
        response.add(countryData2);

        when(restTemplate.getForObject("https://restcountries.com/v3.1/all", List.class))
                .thenReturn(response);

        List<CountryDTO> countries = service.getAllCountries("COUNTRY1", null);

        assertEquals(1, countries.size());
        assertEquals("COUNTRY1", countries.get(0).getCode());
        assertEquals("Country 1", countries.get(0).getName());
    }
    @Test
    void getAllCountriesByName() {
        List<Map<String, Object>> response = new ArrayList<>();

        Map<String, Object> countryData1 = new HashMap<>();
        countryData1.put("name", Map.of("common", "Country 1"));
        countryData1.put("population", 1000000L);
        countryData1.put("area", 100000.0);
        countryData1.put("region", "Region 1");
        countryData1.put("languages", Map.of("es", "Spanish"));
        countryData1.put("cca3", "COUNTRY1");
        countryData1.put("borders", List.of("BORDER1", "BORDER2"));
        response.add(countryData1);

        Map<String, Object> countryData2 = new HashMap<>();
        countryData2.put("name", Map.of("common", "Country 2"));
        countryData2.put("population", 2000000L);
        countryData2.put("area", 200000.0);
        countryData2.put("region", "Region 2");
        countryData2.put("languages", Map.of("en", "English"));
        countryData2.put("cca3", "COUNTRY2");
        countryData2.put("borders", List.of("BORDER3", "BORDER4"));
        response.add(countryData2);

        when(restTemplate.getForObject("https://restcountries.com/v3.1/all", List.class))
                .thenReturn(response);

        List<CountryDTO> countries = service.getAllCountries(null, "Country 1");

        assertEquals(1, countries.size());
        assertEquals("COUNTRY1", countries.get(0).getCode());
        assertEquals("Country 1", countries.get(0).getName());
    }
    @Test
    void getCountriesByContinent() {
        List<Map<String, Object>> response = new ArrayList<>();

        Map<String, Object> countryData1 = new HashMap<>();
        countryData1.put("name", Map.of("common", "Country 1"));
        countryData1.put("population", 1000000L);
        countryData1.put("area", 100000.0);
        countryData1.put("region", "Region 1");
        countryData1.put("languages", Map.of("es", "Spanish"));
        countryData1.put("cca3", "COUNTRY1");
        countryData1.put("borders", List.of("BORDER1", "BORDER2"));
        response.add(countryData1);

        Map<String, Object> countryData2 = new HashMap<>();
        countryData2.put("name", Map.of("common", "Country 2"));
        countryData2.put("population", 2000000L);
        countryData2.put("area", 200000.0);
        countryData2.put("region", "Region 2");
        countryData2.put("languages", Map.of("en", "English"));
        countryData2.put("cca3", "COUNTRY2");
        countryData2.put("borders", List.of("BORDER3", "BORDER4"));
        response.add(countryData2);

        Map<String, Object> countryData3 = new HashMap<>();
        countryData3.put("name", Map.of("common", "Country 3"));
        countryData3.put("population", 3000000L);
        countryData3.put("area", 300000.0);
        countryData3.put("region", "Region 1");
        countryData3.put("languages", Map.of("fr", "French"));
        countryData3.put("cca3", "COUNTRY3");
        countryData3.put("borders", List.of("BORDER5", "BORDER6"));
        response.add(countryData3);

        when(restTemplate.getForObject("https://restcountries.com/v3.1/all", List.class))
                .thenReturn(response);

        List<CountryDTO> countries = service.getCountriesByContinent("Region 1");

        assertEquals(2, countries.size());
        assertEquals("COUNTRY1", countries.get(0).getCode());
        assertEquals("Country 1", countries.get(0).getName());
        assertEquals("COUNTRY3", countries.get(1).getCode());
        assertEquals("Country 3", countries.get(1).getName());
    }

    @Test
    void getCountriesByLenguaje() {
        List<Map<String, Object>> response = new ArrayList<>();

        Map<String, Object> countryData1 = new HashMap<>();
        countryData1.put("name", Map.of("common", "Country 1"));
        countryData1.put("population", 1000000L);
        countryData1.put("area", 100000.0);
        countryData1.put("region", "Region 1");
        countryData1.put("languages", Map.of("es", "Spanish"));
        countryData1.put("cca3", "COUNTRY1");
        countryData1.put("borders", List.of("BORDER1", "BORDER2"));
        response.add(countryData1);

        Map<String, Object> countryData2 = new HashMap<>();
        countryData2.put("name", Map.of("common", "Country 2"));
        countryData2.put("population", 2000000L);
        countryData2.put("area", 200000.0);
        countryData2.put("region", "Region 2");
        countryData2.put("languages", Map.of("en", "English"));
        countryData2.put("cca3", "COUNTRY2");
        countryData2.put("borders", List.of("BORDER3", "BORDER4"));
        response.add(countryData2);

        Map<String, Object> countryData3 = new HashMap<>();
        countryData3.put("name", Map.of("common", "Country 3"));
        countryData3.put("population", 3000000L);
        countryData3.put("area", 300000.0);
        countryData3.put("region", "Region 1");
        countryData3.put("languages", Map.of("fr", "French"));
        countryData3.put("cca3", "COUNTRY3");
        countryData3.put("borders", List.of("BORDER5", "BORDER6"));
        response.add(countryData3);

        when(restTemplate.getForObject("https://restcountries.com/v3.1/all", List.class))
                .thenReturn(response);

        List<CountryDTO> countries = service.getCountriesByLenguaje("Spanish");

        assertEquals(1, countries.size());
        assertEquals("COUNTRY1", countries.get(0).getCode());
        assertEquals("Country 1", countries.get(0).getName());
    }

    @Test
    void getAllCountriesByAmountToSave() {
        CountryToSave request = new CountryToSave();
        request.setAmountOfCountryToSave(5);

        List<Map<String, Object>> response = new ArrayList<>();

        Map<String, Object> countryData1 = new HashMap<>();
        countryData1.put("name", Map.of("common", "Country 1"));
        countryData1.put("population", 1000000L);
        countryData1.put("area", 100000.0);
        countryData1.put("region", "Region 1");
        countryData1.put("languages", Map.of("es", "Spanish"));
        countryData1.put("cca3", "COUNTRY1");
        countryData1.put("borders", List.of("BORDER1", "BORDER2"));
        response.add(countryData1);

        Map<String, Object> countryData2 = new HashMap<>();
        countryData2.put("name", Map.of("common", "Country 2"));
        countryData2.put("population", 2000000L);
        countryData2.put("area", 200000.0);
        countryData2.put("region", "Region 2");
        countryData2.put("languages", Map.of("en", "English"));
        countryData2.put("cca3", "COUNTRY2");
        countryData2.put("borders", List.of("BORDER3", "BORDER4"));
        response.add(countryData2);

        Map<String, Object> countryData3 = new HashMap<>();
        countryData3.put("name", Map.of("common", "Country 3"));
        countryData3.put("population", 3000000L);
        countryData3.put("area", 300000.0);
        countryData3.put("region", "Region 1");
        countryData3.put("languages", Map.of("fr", "French"));
        countryData3.put("cca3", "COUNTRY3");
        countryData3.put("borders", List.of("BORDER5", "BORDER6"));
        response.add(countryData3);

        Map<String, Object> countryData4 = new HashMap<>();
        countryData4.put("name", Map.of("common", "Country 4"));
        countryData4.put("population", 4000000L);
        countryData4.put("area", 400000.0);
        countryData4.put("region", "Region 2");
        countryData4.put("languages", Map.of("de", "German"));
        countryData4.put("cca3", "COUNTRY4");
        countryData4.put("borders", List.of("BORDER7", "BORDER8"));
        response.add(countryData4);

        Map<String, Object> countryData5 = new HashMap<>();
        countryData5.put("name", Map.of("common", "Country 5"));
        countryData5.put("population", 5000000L);
        countryData5.put("area", 500000.0);
        countryData5.put("region", "Region 1");
        countryData5.put("languages", Map.of("it", "Italian"));
        countryData5.put("cca3", "COUNTRY5");
        countryData5.put("borders", List.of("BORDER9", "BORDER10"));
        response.add(countryData5);

        when(restTemplate.getForObject("https://restcountries.com/v3.1/all", List.class))
                .thenReturn(response);

        List<CountryDTO> countries = service.getAllCountriesByAmountToSave(request);

        assertEquals(5, countries.size());
        assertNotNull(countries.get(0).getCode());
        assertNotNull(countries.get(0).getName());
    }

    @Test
    void getAllCountriesByAmountToSave_MaximumLimit() {
        CountryToSave request = new CountryToSave();
        request.setAmountOfCountryToSave(15);

        List<Map<String, Object>> response = new ArrayList<>();

        for (int i = 1; i <= 15; i++) {
            Map<String, Object> countryData = new HashMap<>();
            countryData.put("name", Map.of("common", "Country " + i));
            countryData.put("population", 1000000L * i);
            countryData.put("area", 100000.0 * i);
            countryData.put("region", "Region " + i);
            countryData.put("languages", Map.of("es", "Spanish"));
            countryData.put("cca3", "COUNTRY" + i);
            countryData.put("borders", List.of("BORDER" + i, "BORDER" + (i + 1)));
            response.add(countryData);
        }

        when(restTemplate.getForObject("https://restcountries.com/v3.1/all", List.class))
                .thenReturn(response);

        try {
            service.getAllCountriesByAmountToSave(request);
            fail("Debería lanzar una excepción");
        } catch (IllegalArgumentException e) {
            assertEquals("La cantidad de paises a guardar no puede ser mayor a 10", e.getMessage());
        }
    }
}