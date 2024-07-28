/*
 *
 * You can use the following import statements
 * 
 * java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.geohub.repository;

import com.example.geohub.model.Country;
import java.util.ArrayList;

public interface CountryRepository {
    ArrayList<Country> getCountries();

    Country getCountryById(int countryId);

    Country addCountry(Country country);

    Country updateCountry(int countryId, Country country);

    void deleteCountry(int countryId);
}