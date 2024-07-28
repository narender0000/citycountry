/*
 *
 * You can use the following import statements
 * 
 * import javax.persistence.*;
 * 
 */

// Write your code here
package com.example.geohub.model;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "countryid")
    private int countryId;

    @Column(name = "country")
    private String countryName;

    @Column(name = "currency")
    private String currency;

    @Column(name = "population")
    private long population;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    public Country() {
    }

    public Country(int countryId, String countryName, String currency, long population, String latitude,
            String longitude) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.currency = currency;
        this.population = population;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCurrency() {
        return currency;
    }

    public long getPopulation() {
        return population;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}