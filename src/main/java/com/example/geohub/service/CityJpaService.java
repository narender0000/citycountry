/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.geohub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

import com.example.geohub.model.City;
import com.example.geohub.model.Country;
import com.example.geohub.repository.CityJpaRepository;
import com.example.geohub.repository.CountryJpaRepository;
import com.example.geohub.repository.CityRepository;

@Service
public class CityJpaService implements CityRepository {
    @Autowired
    private CityJpaRepository cityJpaRepository;
    @Autowired
    private CountryJpaRepository countryJpaRepository;

    @Override
    public ArrayList<City> getCities() {
        List<City> cityList = cityJpaRepository.findAll();
        ArrayList<City> cities = new ArrayList<>(cityList);
        return cities;
    }

    @Override
    public City getCityById(int cityId) {
        try {
            City city = cityJpaRepository.findById(cityId).get();
            return city;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public City addCity(City city) {
        Country country = city.getCountry();
        int countryId = country.getCountryId();
        try {
            Country completeCountry = countryJpaRepository.findById(countryId).get();
            city.setCountry(completeCountry);
            cityJpaRepository.save(city);
            return city;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public City updateCity(int cityId, City city) {
        try {
            City newCity = cityJpaRepository.findById(cityId).get();
            if (city.getCityName() != null)
                newCity.setCityName(city.getCityName());
            if (city.getPopulation() != 0)
                newCity.setPopulation(city.getPopulation());
            if (city.getLatitude() != null)
                newCity.setLatitude(city.getLatitude());
            if (city.getLongitude() != null)
                newCity.setLongitude(city.getLongitude());
            if (city.getCountry() != null) {
                Country country = city.getCountry();
                int countryId = country.getCountryId();
                Country newCountry = countryJpaRepository.findById(countryId).get();
                newCity.setCountry(newCountry);
            }

            cityJpaRepository.save(newCity);
            return newCity;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteCity(int cityId) {
        try {
            cityJpaRepository.deleteById(cityId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Country getCityCountry(int cityId) {
        try {
            City city = cityJpaRepository.findById(cityId).get();
            return city.getCountry();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
