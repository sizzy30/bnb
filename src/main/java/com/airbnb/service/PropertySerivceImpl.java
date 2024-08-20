package com.airbnb.service;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import com.airbnb.entity.Property;
import com.airbnb.payload.PropertyDto;
import com.airbnb.repository.CityRepository;
import com.airbnb.repository.CountryRepository;
import com.airbnb.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertySerivceImpl implements PropertyService{
    private PropertyRepository propertyRepository;
    private CityRepository cityRepository ;
    private CountryRepository countryRepository ;

    public PropertySerivceImpl(PropertyRepository propertyRepository, CityRepository cityRepository, CountryRepository countryRepository) {
        this.propertyRepository = propertyRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public PropertyDto addProperty(PropertyDto dto, long cityId, long countryId) {
        City city = cityRepository.findById(cityId).get();
        Country country = countryRepository.findById(countryId).get();
        Property property =mapToEntity(dto);
        property.setCity(city);
        property.setCountry(country);
        Property save = propertyRepository.save(property);
        PropertyDto res =mapToDto(save);
        return res;
    }

    @Override
    public Boolean deleteProperty(long id) {
        Optional<Property> optionalProperty=propertyRepository.findById(id);
        if(optionalProperty.isPresent()){
        propertyRepository.deleteById(id);
        return true;}
        return false;
    }

    private PropertyDto mapToDto(Property save) {
        PropertyDto propertyDto =new PropertyDto();
        propertyDto.setId(save.getId());
        propertyDto.setName(save.getName());
        propertyDto.setNumber_of_bedrooms(save.getNumber_of_bedrooms());
        propertyDto.setNumber_of_beds(save.getNumber_of_beds());
        propertyDto.setNumber_of_bathrooms(save.getNumber_of_bathrooms());
        propertyDto.setNumber_of_guests(save.getNumber_of_guests());
        propertyDto.setCity(save.getCity());
        propertyDto.setCountry(save.getCountry());
        return propertyDto;
    }

    private Property mapToEntity(PropertyDto dto) {
        Property property=new Property();
        property.setName(dto.getName());
        property.setNumber_of_bedrooms(dto.getNumber_of_bedrooms());
        property.setNumber_of_bathrooms(dto.getNumber_of_bathrooms());
        property.setNumber_of_beds(dto.getNumber_of_beds());
        property.setNumber_of_guests(dto.getNumber_of_guests());
        return property;
    }
}
