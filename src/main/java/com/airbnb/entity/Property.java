package com.airbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number_of_beds", nullable = false)
    private String number_of_beds;

    @Column(name = "number_of_guests", nullable = false)
    private String number_of_guests;

    @Column(name = "number_of_bedrooms", nullable = false)
    private String number_of_bedrooms;

    @Column(name = "number_of_bathrooms", nullable = false)
    private String number_of_bathrooms;


    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

}