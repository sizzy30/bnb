package com.airbnb.controller;

import com.airbnb.payload.CityDto;
import com.airbnb.repository.CityRepository;
import com.airbnb.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/city")
public class CityController {
    private CityService cityService;


    public CityController(CityService cityService
                        ) {
        this.cityService = cityService;

    }
    @PostMapping("/createCity")
    public ResponseEntity<CityDto> create(@RequestBody CityDto cityDto){
        CityDto dto=cityService.addCity(cityDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteCity")
    public ResponseEntity<String> delete(@RequestParam long cityId){
        Boolean val=cityService.deleteCity(cityId);
        if(val){
            return new ResponseEntity<>("deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
    }
}
