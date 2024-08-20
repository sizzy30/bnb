package com.airbnb.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.airbnb.payload.PropertyDto;
import com.airbnb.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyService propertyService ;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;

    }
    @PostMapping("/createProperty")
    public ResponseEntity<PropertyDto> create(@RequestBody PropertyDto dto, @RequestParam long cityId,@RequestParam long countryId){
        PropertyDto propertyDto =propertyService.addProperty(dto,cityId,countryId);
        return new ResponseEntity<>(propertyDto, HttpStatus.CREATED);

    }
    @DeleteMapping("/deleteProperty")
    public ResponseEntity<String> delete(@RequestParam long id){
        Boolean val=propertyService.deleteProperty(id);
        if(val){
            return new ResponseEntity<>("deleted",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
        }
    }
}
