package ar.edu.utn.frc.tup.lciii.service.serviceImp;

import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.model.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.CountryToSave;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryServiceImp {

    List<CountryDTO> getAllCountries(String code, String name);

    List<CountryDTO> getCountriesByContinent(String region);

    List<CountryDTO> getCountriesWithMostBorders();

    List<CountryDTO> getCountriesByLenguaje(String languages);

    List<CountryDTO> getAllCountriesByAmountToSave(CountryToSave request);
}
