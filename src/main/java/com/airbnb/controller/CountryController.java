package com.airbnb.controller;

import com.airbnb.payload.CountryDto;
import com.airbnb.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    private CountryService countryService ;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/createCountry")
    public ResponseEntity<CountryDto> create(@RequestBody CountryDto dto ){
        CountryDto res=countryService.addCountry(dto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteCountry")
    public ResponseEntity<String> delete(@RequestParam long countryId){
        Boolean val=countryService.delete(countryId);
        if(val){
            return new ResponseEntity<>("deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
    }
}
