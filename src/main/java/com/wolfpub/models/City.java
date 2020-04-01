package com.wolfpub.models;

public class City {
    private String city;
    private int location;

    public City(String city, int location) {
        this.city = city;
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
