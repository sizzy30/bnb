package com.airbnb.service;

import com.airbnb.payload.CountryDto;

public interface CountryService {
    CountryDto addCountry(CountryDto dto);

    Boolean delete(long countryId);
}
