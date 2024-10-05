package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.model.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.CountryToSave;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/countries")
    public ResponseEntity<List<CountryDTO>> getAllCountries(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name) {
        return ResponseEntity.ok(countryService.getAllCountries(
                code != null ? code.trim() : null,
                name != null ? name.trim() : null
        ));
    }

    @GetMapping("/countries/{continent}/continent")
    public ResponseEntity<List<CountryDTO>> getCountriesByContinent(
            @PathVariable String continent) {
        return ResponseEntity.ok(countryService.getCountriesByContinent(
                continent
        ));
    }

    @GetMapping("countries/{language}/language")
    public ResponseEntity<List<CountryDTO>> getCountriesByLenguaje(
            @PathVariable String language) {
        return ResponseEntity.ok(countryService.getCountriesByLenguaje(language));
    }

    @PostMapping("/countries")
    public ResponseEntity<List<CountryDTO>> getAllCountriesByAmountToSave(
            @RequestBody CountryToSave request) {
        return ResponseEntity.ok(countryService.getAllCountriesByAmountToSave(request));
    }

    @GetMapping("/countries/most-borders")
    public ResponseEntity<List<CountryDTO>> getCountriesWithMostBorders() {
        return ResponseEntity.ok(countryService.getCountriesWithMostBorders());
    }



}