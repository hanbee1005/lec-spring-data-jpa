package com.example.lecspringdatajpa.lec01;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;

    private String city;

    private String state;

    private String zipCode;
}
