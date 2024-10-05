package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.entities.CountryEntity;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.model.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.CountryToSave;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import ar.edu.utn.frc.tup.lciii.service.serviceImp.CountryServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService implements CountryServiceImp {

        @Autowired
        public final RestTemplate restTemplate;
        @Autowired
        private final CountryRepository countryRepository;

        @Override
        public List<CountryDTO> getAllCountries(String code, String name) {
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);

                List<Country> countries = response.stream()
                        .map(this::mapToCountry)
                        .collect(Collectors.toList());

                List<Country> countriesFiltrados = countries.stream()
                        .filter(country -> (code == null || country.getCode().equalsIgnoreCase(code.trim())) &&
                                (name == null || country.getName().equalsIgnoreCase(name.trim())))
                        .collect(Collectors.toList());

                return countriesFiltrados.stream()
                        .map(this::mapToDTO)
                        .collect(Collectors.toList());
        }



        @Override
        public List<CountryDTO> getCountriesByContinent(String region) {
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);

                List<Country> countries = response.stream()
                        .map(this::mapToCountry)
                        .collect(Collectors.toList());

                List<Country> countriesFiltrados = countries.stream()
                        .filter(country -> country.getRegion().equalsIgnoreCase(region.trim()))
                        .collect(Collectors.toList());

                return countriesFiltrados.stream()
                        .map(this::mapToDTO)
                        .collect(Collectors.toList());
        }

        @Override
        public List<CountryDTO> getCountriesWithMostBorders() {
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);

                List<Country> countries = response.stream()
                        .map(this::mapToCountry)
                        .collect(Collectors.toList());

                if (countries.isEmpty()) {
                        return Collections.emptyList();
                }

                countries.sort((c1, c2) -> {
                        int borders1 = (c1.getBorders() != null) ? c1.getBorders().size() : 0;
                        int borders2 = (c2.getBorders() != null) ? c2.getBorders().size() : 0;
                        return Integer.compare(borders2, borders1); // Ordenar de mayor a menor
                });

                Country countryWithMostBorders = countries.get(0);

                return Collections.singletonList(mapToDTO(countryWithMostBorders));
        }

        @Override
        public List<CountryDTO> getCountriesByLenguaje(String languages) {
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);

                List<Country> countries = response.stream()
                        .map(this::mapToCountry)
                        .collect(Collectors.toList());

                List<Country> countriesFiltrados = countries.stream()
                        .filter(country -> country.getLanguages() != null && country.getLanguages().containsKey(languages.trim()))
                        .collect(Collectors.toList());

                return countriesFiltrados.stream()
                        .map(this::mapToDTO)
                        .collect(Collectors.toList());
        }

        /**
         * Agregar mapeo de campo cca3 (String)
         * Agregar mapeo campos borders ((List<String>))
         */
        private Country mapToCountry(Map<String, Object> countryData) {
                Map<String, Object> nameData = (Map<String, Object>) countryData.get("name");
                return Country.builder()
                        .name((String) nameData.get("common"))
                        .population(((Number) countryData.get("population")).longValue())
                        .area(((Number) countryData.get("area")).doubleValue())
                        .region((String) countryData.get("region"))
                        .languages((Map<String, String>) countryData.get("languages"))
                        .code((String) countryData.get("cca3"))
                        .borders((List<String>) countryData.get("borders"))
                        .build();
        }


        private CountryDTO mapToDTO(Country country) {
                return new CountryDTO(country.getCode(), country.getName());
        }

        private CountryEntity mapToEntity(Country country) {
                CountryEntity countryEntity = new CountryEntity();

                countryEntity.setName(country.getName());
                countryEntity.setArea(country.getArea());
                countryEntity.setCode(country.getCode());
                countryEntity.setPopulation(country.getPopulation());
                return countryEntity;
        }

        @Override
        public List<CountryDTO> getAllCountriesByAmountToSave(CountryToSave request) {
                String url = "https://restcountries.com/v3.1/all";

                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);

                List<Country> countries = response.stream()
                        .map(this::mapToCountry)
                        .collect(Collectors.toList());

                if(request.getAmountOfCountryToSave() > 10) {
                        throw new IllegalArgumentException("La cantidad de paises a guardar no puede ser mayor a 10");
                }
                int amountToSave = Math.min(request.getAmountOfCountryToSave(), 10);

                List<CountryEntity> countriesToSave = countries.stream()
                        .map(this::mapToEntity)
                        .collect(Collectors.toList());

                Collections.shuffle(countries);
                List<CountryEntity> selectedCountries = countriesToSave.stream()
                        .limit(amountToSave)
                        .collect(Collectors.toList());

                countryRepository.saveAll(selectedCountries);

                return selectedCountries.stream()
                        .map(country -> new CountryDTO(country.getCode(), country.getName()))
                        .collect(Collectors.toList());
        }


}