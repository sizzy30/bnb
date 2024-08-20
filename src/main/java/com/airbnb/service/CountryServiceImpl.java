package com.airbnb.service;

import com.airbnb.entity.Country;
import com.airbnb.payload.CountryDto;
import com.airbnb.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService{
    @Override
    @Transactional
    public Boolean delete(long countryId) {
        Optional<Country> country =countryRepository.findById(countryId);
        if(country.isPresent()){
            Country country1 = country.get();
            country1.getProperties().forEach(x->x.setCountry(null));
            countryRepository.deleteById(countryId);
            return true;
        }
        return false;
    }

    private CountryRepository countryRepository ;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public CountryDto addCountry(CountryDto dto) {
        Country country =mapToEntity(dto);
        Country save = countryRepository.save(country);
        CountryDto countryDto =mapToDto(save);
        return countryDto;
    }

    private CountryDto mapToDto(Country c) {
        CountryDto dto =new CountryDto();
        dto.setId(c.getId());
        dto.setName(c.getName());
        return dto;
    }

    private Country mapToEntity(CountryDto dto) {
        Country country=new Country();
        country.setName(dto.getName());
        return country;
    }
}
