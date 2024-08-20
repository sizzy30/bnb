package com.airbnb.payload;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDto {
    private long id;
    private String name;
    private String number_of_beds;
    private String number_of_bedrooms;
    private String number_of_guests;
    private String number_of_bathrooms;
    private City city;
    private Country country;
}
