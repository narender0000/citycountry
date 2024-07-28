package com.example.geohub;



import com.example.geohub.controller.CityController;
import com.example.geohub.controller.CountryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private CityController cityController;

    @Autowired
    private CountryController countryController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(cityController).isNotNull();
        assertThat(countryController).isNotNull();
    }
}
