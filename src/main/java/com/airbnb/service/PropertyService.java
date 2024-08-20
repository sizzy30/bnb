package com.airbnb.service;

import com.airbnb.payload.PropertyDto;

public interface PropertyService {
    PropertyDto addProperty(PropertyDto dto, long cityId, long countryId);

    Boolean deleteProperty(long id);
}
