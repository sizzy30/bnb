package com.airbnb.service;

import com.airbnb.entity.City;
import com.airbnb.payload.CityDto;
import com.airbnb.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CityServiceImpl implements CityService{
    @Override
    @Transactional
    public Boolean deleteCity(long cityId) {
Optional<City> cityOptional =cityRepository.findById(cityId);
if(cityOptional.isPresent()) {
    City city = cityOptional.get();
    city.getProperties().forEach(property -> property.setCity(null));
    cityRepository.deleteById(cityId);
    return true;
}else{
    return false;
}
    }

    private CityRepository cityRepository ;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public CityDto addCity(CityDto cityDto) {
        City city =mapToEntity(cityDto);
        City save = cityRepository.save(city);
        CityDto cityDto1 =mapToDto(save);
        return cityDto1;
    }

    private CityDto mapToDto(City city) {
        CityDto dto =new CityDto();
        dto.setId(city.getId());
        dto.setName(city.getName());
        return dto;
    }

    private City mapToEntity(CityDto cityDto) {
        City city=new City();
        city.setName(cityDto.getName());
        return city;
    }
}
