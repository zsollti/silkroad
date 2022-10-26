package com.codecool.shop.model;

public class Customer {

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String country;
    private final String city;
    private final String zip;


    public Customer(String name, String phone, String email, String address,String country,String city,String zip) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.country = country;
        this.city = city;
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}