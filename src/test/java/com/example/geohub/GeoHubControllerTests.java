package com.example.geohub;

import com.example.geohub.model.City;
import com.example.geohub.model.Country;
import com.example.geohub.repository.CityJpaRepository;
import com.example.geohub.repository.CountryJpaRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.HashMap;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql(scripts = { "/schema.sql", "/data.sql" })
public class GeoHubControllerTests {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private CityJpaRepository cityJpaRepository;

        @Autowired
        private CountryJpaRepository countryJpaRepository;

        @Autowired
        private JdbcTemplate jdbcTemplate;

        private HashMap<Integer, Object[]> countries = new HashMap<>();
        {
                countries.put(1, new Object[] { "India", "INR", 1393409038, "20.5937° N", "78.9629° E" });
                countries.put(2, new Object[] { "USA", "USD", 331893745, "37.7749° N", "122.4194° W" });
                countries.put(3, new Object[] { "Australia", "AUD", 25687041, "25.2744° S", "133.7751° E" });
                countries.put(4, new Object[] { "Canada", "CAD", 38008005, "56.1304° N", "106.3468° W" });
                countries.put(5, new Object[] { "UK", "GBP", 68207116, "51.5074° N", "0.1278° W" });
                countries.put(6, new Object[] { "Germany", "EUR", 83166711, "51.1657° N", "10.4515° E" }); // POST
                countries.put(7, new Object[] { "United Kingdom", "GBP", 68207116, "51.5074° N", "0.1278° W" }); // PUT
        }

        private HashMap<Integer, Object[]> cities = new HashMap<>();
        {
                cities.put(1, new Object[] { "Mumbai", 20185064, "19.0760° N", "72.8777° E", 1 });
                cities.put(2, new Object[] { "Bangalore", 12425304, "12.9716° N", "77.5946° E", 1 });
                cities.put(3, new Object[] { "New York", 8419600, "40.7128° N", "74.0060° W", 2 });
                cities.put(4, new Object[] { "Chicago", 2716000, "41.8781° N", "87.6298° W", 2 });
                cities.put(5, new Object[] { "Sydney", 5303000, "33.8688° S", "151.2093° E", 3 });
                cities.put(6, new Object[] { "Melbourne", 5084000, "37.8136° S", "144.9631° E", 3 });
                cities.put(7, new Object[] { "Vancouver", 675218, "49.2827° N", "123.1207° W", 4 });
                cities.put(8, new Object[] { "Toronto", 3140000, "43.651070° N", "79.347015° W", 4 });
                cities.put(9, new Object[] { "London", 8982000, "51.5074° N", "0.1278° W", 5 });
                cities.put(10, new Object[] { "Manchester", 547627, "53.4808° N", "2.2426° W", 5 });
                cities.put(11, new Object[] { "Delhi", 30291000, "28.7041° N", "77.1025° E", 1 }); // POST
                cities.put(12, new Object[] { "Los Angeles", 3980400, "34.0522° N", "118.2437° W", 2 }); // PUT
        }

        @Test
        @Order(1)
        public void testGetCountries() throws Exception {
                mockMvc.perform(get("/countries")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", Matchers.hasSize(5)))

                                .andExpect(jsonPath("$[0].countryId", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$[0].countryName", Matchers.equalTo(countries.get(1)[0])))
                                .andExpect(jsonPath("$[0].currency", Matchers.equalTo(countries.get(1)[1])))
                                .andExpect(jsonPath("$[0].population", Matchers.equalTo(countries.get(1)[2])))
                                .andExpect(jsonPath("$[0].latitude", Matchers.equalTo(countries.get(1)[3])))
                                .andExpect(jsonPath("$[0].longitude", Matchers.equalTo(countries.get(1)[4])))

                                .andExpect(jsonPath("$[1].countryId", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$[1].countryName", Matchers.equalTo(countries.get(2)[0])))
                                .andExpect(jsonPath("$[1].currency", Matchers.equalTo(countries.get(2)[1])))
                                .andExpect(jsonPath("$[1].population", Matchers.equalTo(countries.get(2)[2])))
                                .andExpect(jsonPath("$[1].latitude", Matchers.equalTo(countries.get(2)[3])))
                                .andExpect(jsonPath("$[1].longitude", Matchers.equalTo(countries.get(2)[4])))

                                .andExpect(jsonPath("$[2].countryId", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$[2].countryName", Matchers.equalTo(countries.get(3)[0])))
                                .andExpect(jsonPath("$[2].currency", Matchers.equalTo(countries.get(3)[1])))
                                .andExpect(jsonPath("$[2].population", Matchers.equalTo(countries.get(3)[2])))
                                .andExpect(jsonPath("$[2].latitude", Matchers.equalTo(countries.get(3)[3])))
                                .andExpect(jsonPath("$[2].longitude", Matchers.equalTo(countries.get(3)[4])))

                                .andExpect(jsonPath("$[3].countryId", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$[3].countryName", Matchers.equalTo(countries.get(4)[0])))
                                .andExpect(jsonPath("$[3].currency", Matchers.equalTo(countries.get(4)[1])))
                                .andExpect(jsonPath("$[3].population", Matchers.equalTo(countries.get(4)[2])))
                                .andExpect(jsonPath("$[3].latitude", Matchers.equalTo(countries.get(4)[3])))
                                .andExpect(jsonPath("$[3].longitude", Matchers.equalTo(countries.get(4)[4])))

                                .andExpect(jsonPath("$[4].countryId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$[4].countryName", Matchers.equalTo(countries.get(5)[0])))
                                .andExpect(jsonPath("$[4].currency", Matchers.equalTo(countries.get(5)[1])))
                                .andExpect(jsonPath("$[4].population", Matchers.equalTo(countries.get(5)[2])))
                                .andExpect(jsonPath("$[4].latitude", Matchers.equalTo(countries.get(5)[3])))
                                .andExpect(jsonPath("$[4].longitude", Matchers.equalTo(countries.get(5)[4])));
        }

        @Test
        @Order(2)
        public void testGetCountryNotFound() throws Exception {
                mockMvc.perform(get("/countries/48")).andExpect(status().isNotFound());
        }

        @Test
        @Order(3)
        public void testGetCountryById() throws Exception {
                mockMvc.perform(get("/countries/1")).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(1)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(1)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(1)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(1)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(1)[4])));

                mockMvc.perform(get("/countries/2")).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(2)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(2)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(2)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(2)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(2)[4])));

                mockMvc.perform(get("/countries/3")).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(3)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(3)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(3)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(3)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(3)[4])));

                mockMvc.perform(get("/countries/4")).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(4)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(4)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(4)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(4)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(4)[4])));

                mockMvc.perform(get("/countries/5")).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(5)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(5)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(5)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(5)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(5)[4])));
        }

        @Test
        @Order(4)
        public void testPostCountry() throws Exception {
                String content = "{\"countryName\": \"" + countries.get(6)[0] + "\", \"currency\": \""
                                + countries.get(6)[1]
                                + "\", \"population\": " + countries.get(6)[2] + ", \"latitude\": \""
                                + countries.get(6)[3]
                                + "\", \"longitude\": \"" + countries.get(6)[4] + "\"}";

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/countries")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(6)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(6)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(6)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(6)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(6)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(6)[4])));
        }

        @Test
        @Order(5)
        public void testAfterPostCountry() throws Exception {
                mockMvc.perform(get("/countries/6")).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(6)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(6)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(6)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(6)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(6)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(6)[4])));
        }

        @Test
        @Order(6)
        public void testDbAfterPostCountry() throws Exception {
                Country country = countryJpaRepository.findById(6).get();

                assertEquals(country.getCountryId(), 6);
                assertEquals(country.getCountryName(), countries.get(6)[0]);
                assertEquals(country.getCurrency(), countries.get(6)[1]);
                assertEquals(country.getPopulation(), Long.parseLong(Integer.toString((int) countries.get(6)[2])));
                assertEquals(country.getLatitude(), countries.get(6)[3]);
                assertEquals(country.getLongitude(), countries.get(6)[4]);

        }

        @Test
        @Order(7)
        public void testPutCountryNotFound() throws Exception {
                String content = "{\"countryName\": \"" + countries.get(7)[0] + "\", \"currency\": \""
                                + countries.get(7)[1]
                                + "\", \"population\": " + countries.get(7)[2] + ", \"latitude\": \""
                                + countries.get(7)[3]
                                + "\", \"longitude\": \"" + countries.get(7)[4] + "\"}";

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/countries/48")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest).andExpect(status().isNotFound());

        }

        @Test
        @Order(8)
        public void testPutCountry() throws Exception {
                String content = "{\"countryName\": \"" + countries.get(7)[0] + "\", \"currency\": \""
                                + countries.get(7)[1]
                                + "\", \"population\": " + countries.get(7)[2] + ", \"latitude\": \""
                                + countries.get(7)[3]
                                + "\", \"longitude\": \"" + countries.get(7)[4] + "\"}";

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/countries/5")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(7)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(7)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(7)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(7)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(7)[4])));

        }

        @Test
        @Order(9)
        public void testAfterPutCountry() throws Exception {

                mockMvc.perform(get("/countries/5")).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(7)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(7)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(7)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(7)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(7)[4])));

        }

        @Test
        @Order(10)
        public void testDbAfterPutCountry() throws Exception {
                Country country = countryJpaRepository.findById(5).get();

                assertEquals(country.getCountryId(), 5);
                assertEquals(country.getCountryName(), countries.get(7)[0]);
                assertEquals(country.getCurrency(), countries.get(7)[1]);
                assertEquals(country.getPopulation(), Long.parseLong(Integer.toString((int) countries.get(7)[2])));
                assertEquals(country.getLatitude(), countries.get(7)[3]);
                assertEquals(country.getLongitude(), countries.get(7)[4]);
        }

        @Test
        @Order(11)
        public void testDeleteCountryNotFound() throws Exception {

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/countries/148")
                                .contentType(MediaType.APPLICATION_JSON);
                mockMvc.perform(mockRequest).andExpect(status().isNotFound());

        }

        @Test
        @Order(12)
        public void testDeleteCountry() throws Exception {

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/countries/6")
                                .contentType(MediaType.APPLICATION_JSON);
                mockMvc.perform(mockRequest).andExpect(status().isNoContent());
        }

        @Test
        @Order(13)
        public void testAfterDeleteCountry() throws Exception {
                mockMvc.perform(get("/countries")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", Matchers.hasSize(5)))

                                .andExpect(jsonPath("$[0].countryId", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$[0].countryName", Matchers.equalTo(countries.get(1)[0])))
                                .andExpect(jsonPath("$[0].currency", Matchers.equalTo(countries.get(1)[1])))
                                .andExpect(jsonPath("$[0].population", Matchers.equalTo(countries.get(1)[2])))
                                .andExpect(jsonPath("$[0].latitude", Matchers.equalTo(countries.get(1)[3])))
                                .andExpect(jsonPath("$[0].longitude", Matchers.equalTo(countries.get(1)[4])))

                                .andExpect(jsonPath("$[1].countryId", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$[1].countryName", Matchers.equalTo(countries.get(2)[0])))
                                .andExpect(jsonPath("$[1].currency", Matchers.equalTo(countries.get(2)[1])))
                                .andExpect(jsonPath("$[1].population", Matchers.equalTo(countries.get(2)[2])))
                                .andExpect(jsonPath("$[1].latitude", Matchers.equalTo(countries.get(2)[3])))
                                .andExpect(jsonPath("$[1].longitude", Matchers.equalTo(countries.get(2)[4])))

                                .andExpect(jsonPath("$[2].countryId", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$[2].countryName", Matchers.equalTo(countries.get(3)[0])))
                                .andExpect(jsonPath("$[2].currency", Matchers.equalTo(countries.get(3)[1])))
                                .andExpect(jsonPath("$[2].population", Matchers.equalTo(countries.get(3)[2])))
                                .andExpect(jsonPath("$[2].latitude", Matchers.equalTo(countries.get(3)[3])))
                                .andExpect(jsonPath("$[2].longitude", Matchers.equalTo(countries.get(3)[4])))

                                .andExpect(jsonPath("$[3].countryId", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$[3].countryName", Matchers.equalTo(countries.get(4)[0])))
                                .andExpect(jsonPath("$[3].currency", Matchers.equalTo(countries.get(4)[1])))
                                .andExpect(jsonPath("$[3].population", Matchers.equalTo(countries.get(4)[2])))
                                .andExpect(jsonPath("$[3].latitude", Matchers.equalTo(countries.get(4)[3])))
                                .andExpect(jsonPath("$[3].longitude", Matchers.equalTo(countries.get(4)[4])))

                                .andExpect(jsonPath("$[4].countryId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$[4].countryName", Matchers.equalTo(countries.get(7)[0])))
                                .andExpect(jsonPath("$[4].currency", Matchers.equalTo(countries.get(7)[1])))
                                .andExpect(jsonPath("$[4].population", Matchers.equalTo(countries.get(7)[2])))
                                .andExpect(jsonPath("$[4].latitude", Matchers.equalTo(countries.get(7)[3])))
                                .andExpect(jsonPath("$[4].longitude", Matchers.equalTo(countries.get(7)[4])));
        }

        @Test
        @Order(14)
        public void testGetCities() throws Exception {
                mockMvc.perform(get("/countries/cities")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", Matchers.hasSize(10)))

                                .andExpect(jsonPath("$[0].cityId", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$[0].cityName", Matchers.equalTo(cities.get(1)[0])))
                                .andExpect(jsonPath("$[0].population", Matchers.equalTo(cities.get(1)[1])))
                                .andExpect(jsonPath("$[0].latitude", Matchers.equalTo(cities.get(1)[2])))
                                .andExpect(jsonPath("$[0].longitude", Matchers.equalTo(cities.get(1)[3])))
                                .andExpect(jsonPath("$[0].country.countryId",
                                                Matchers.equalTo(cities.get(1)[4])))
                                .andExpect(jsonPath("$[0].country.countryName",
                                                Matchers.equalTo(countries.get(1)[0])))
                                .andExpect(jsonPath("$[0].country.currency",
                                                Matchers.equalTo(countries.get(1)[1])))
                                .andExpect(jsonPath("$[0].country.population",
                                                Matchers.equalTo(countries.get(1)[2])))
                                .andExpect(jsonPath("$[0].country.latitude",
                                                Matchers.equalTo(countries.get(1)[3])))
                                .andExpect(jsonPath("$[0].country.longitude",
                                                Matchers.equalTo(countries.get(1)[4])))

                                .andExpect(jsonPath("$[1].cityId", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$[1].cityName", Matchers.equalTo(cities.get(2)[0])))
                                .andExpect(jsonPath("$[1].population", Matchers.equalTo(cities.get(2)[1])))
                                .andExpect(jsonPath("$[1].latitude", Matchers.equalTo(cities.get(2)[2])))
                                .andExpect(jsonPath("$[1].longitude", Matchers.equalTo(cities.get(2)[3])))
                                .andExpect(jsonPath("$[1].country.countryId",
                                                Matchers.equalTo(cities.get(2)[4])))
                                .andExpect(jsonPath("$[1].country.countryName",
                                                Matchers.equalTo(countries.get(1)[0])))
                                .andExpect(jsonPath("$[1].country.currency",
                                                Matchers.equalTo(countries.get(1)[1])))
                                .andExpect(jsonPath("$[1].country.population",
                                                Matchers.equalTo(countries.get(1)[2])))
                                .andExpect(jsonPath("$[1].country.latitude",
                                                Matchers.equalTo(countries.get(1)[3])))
                                .andExpect(jsonPath("$[1].country.longitude",
                                                Matchers.equalTo(countries.get(1)[4])))

                                .andExpect(jsonPath("$[2].cityId", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$[2].cityName", Matchers.equalTo(cities.get(3)[0])))
                                .andExpect(jsonPath("$[2].population", Matchers.equalTo(cities.get(3)[1])))
                                .andExpect(jsonPath("$[2].latitude", Matchers.equalTo(cities.get(3)[2])))
                                .andExpect(jsonPath("$[2].longitude", Matchers.equalTo(cities.get(3)[3])))
                                .andExpect(jsonPath("$[2].country.countryId",
                                                Matchers.equalTo(cities.get(3)[4])))
                                .andExpect(jsonPath("$[2].country.countryName",
                                                Matchers.equalTo(countries.get(2)[0])))
                                .andExpect(jsonPath("$[2].country.currency",
                                                Matchers.equalTo(countries.get(2)[1])))
                                .andExpect(jsonPath("$[2].country.population",
                                                Matchers.equalTo(countries.get(2)[2])))
                                .andExpect(jsonPath("$[2].country.latitude",
                                                Matchers.equalTo(countries.get(2)[3])))
                                .andExpect(jsonPath("$[2].country.longitude",
                                                Matchers.equalTo(countries.get(2)[4])))

                                .andExpect(jsonPath("$[3].cityId", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$[3].cityName", Matchers.equalTo(cities.get(4)[0])))
                                .andExpect(jsonPath("$[3].population", Matchers.equalTo(cities.get(4)[1])))
                                .andExpect(jsonPath("$[3].latitude", Matchers.equalTo(cities.get(4)[2])))
                                .andExpect(jsonPath("$[3].longitude", Matchers.equalTo(cities.get(4)[3])))
                                .andExpect(jsonPath("$[3].country.countryId",
                                                Matchers.equalTo(cities.get(4)[4])))
                                .andExpect(jsonPath("$[3].country.countryName",
                                                Matchers.equalTo(countries.get(2)[0])))
                                .andExpect(jsonPath("$[3].country.currency",
                                                Matchers.equalTo(countries.get(2)[1])))
                                .andExpect(jsonPath("$[3].country.population",
                                                Matchers.equalTo(countries.get(2)[2])))
                                .andExpect(jsonPath("$[3].country.latitude",
                                                Matchers.equalTo(countries.get(2)[3])))
                                .andExpect(jsonPath("$[3].country.longitude",
                                                Matchers.equalTo(countries.get(2)[4])))

                                .andExpect(jsonPath("$[4].cityId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$[4].cityName", Matchers.equalTo(cities.get(5)[0])))
                                .andExpect(jsonPath("$[4].population", Matchers.equalTo(cities.get(5)[1])))
                                .andExpect(jsonPath("$[4].latitude", Matchers.equalTo(cities.get(5)[2])))
                                .andExpect(jsonPath("$[4].longitude", Matchers.equalTo(cities.get(5)[3])))
                                .andExpect(jsonPath("$[4].country.countryId",
                                                Matchers.equalTo(cities.get(5)[4])))
                                .andExpect(jsonPath("$[4].country.countryName",
                                                Matchers.equalTo(countries.get(3)[0])))
                                .andExpect(jsonPath("$[4].country.currency",
                                                Matchers.equalTo(countries.get(3)[1])))
                                .andExpect(jsonPath("$[4].country.population",
                                                Matchers.equalTo(countries.get(3)[2])))
                                .andExpect(jsonPath("$[4].country.latitude",
                                                Matchers.equalTo(countries.get(3)[3])))
                                .andExpect(jsonPath("$[4].country.longitude",
                                                Matchers.equalTo(countries.get(3)[4])))

                                .andExpect(jsonPath("$[5].cityId", Matchers.equalTo(6)))
                                .andExpect(jsonPath("$[5].cityName", Matchers.equalTo(cities.get(6)[0])))
                                .andExpect(jsonPath("$[5].population", Matchers.equalTo(cities.get(6)[1])))
                                .andExpect(jsonPath("$[5].latitude", Matchers.equalTo(cities.get(6)[2])))
                                .andExpect(jsonPath("$[5].longitude", Matchers.equalTo(cities.get(6)[3])))
                                .andExpect(jsonPath("$[5].country.countryId",
                                                Matchers.equalTo(cities.get(6)[4])))
                                .andExpect(jsonPath("$[5].country.countryName",
                                                Matchers.equalTo(countries.get(3)[0])))
                                .andExpect(jsonPath("$[5].country.currency",
                                                Matchers.equalTo(countries.get(3)[1])))
                                .andExpect(jsonPath("$[5].country.population",
                                                Matchers.equalTo(countries.get(3)[2])))
                                .andExpect(jsonPath("$[5].country.latitude",
                                                Matchers.equalTo(countries.get(3)[3])))
                                .andExpect(jsonPath("$[5].country.longitude",
                                                Matchers.equalTo(countries.get(3)[4])))

                                .andExpect(jsonPath("$[6].cityId", Matchers.equalTo(7)))
                                .andExpect(jsonPath("$[6].cityName", Matchers.equalTo(cities.get(7)[0])))
                                .andExpect(jsonPath("$[6].population", Matchers.equalTo(cities.get(7)[1])))
                                .andExpect(jsonPath("$[6].latitude", Matchers.equalTo(cities.get(7)[2])))
                                .andExpect(jsonPath("$[6].longitude", Matchers.equalTo(cities.get(7)[3])))
                                .andExpect(jsonPath("$[6].country.countryId",
                                                Matchers.equalTo(cities.get(7)[4])))
                                .andExpect(jsonPath("$[6].country.countryName",
                                                Matchers.equalTo(countries.get(4)[0])))
                                .andExpect(jsonPath("$[6].country.currency",
                                                Matchers.equalTo(countries.get(4)[1])))
                                .andExpect(jsonPath("$[6].country.population",
                                                Matchers.equalTo(countries.get(4)[2])))
                                .andExpect(jsonPath("$[6].country.latitude",
                                                Matchers.equalTo(countries.get(4)[3])))
                                .andExpect(jsonPath("$[6].country.longitude",
                                                Matchers.equalTo(countries.get(4)[4])))

                                .andExpect(jsonPath("$[7].cityId", Matchers.equalTo(8)))
                                .andExpect(jsonPath("$[7].cityName", Matchers.equalTo(cities.get(8)[0])))
                                .andExpect(jsonPath("$[7].population", Matchers.equalTo(cities.get(8)[1])))
                                .andExpect(jsonPath("$[7].latitude", Matchers.equalTo(cities.get(8)[2])))
                                .andExpect(jsonPath("$[7].longitude", Matchers.equalTo(cities.get(8)[3])))
                                .andExpect(jsonPath("$[7].country.countryId",
                                                Matchers.equalTo(cities.get(8)[4])))
                                .andExpect(jsonPath("$[7].country.countryName",
                                                Matchers.equalTo(countries.get(4)[0])))
                                .andExpect(jsonPath("$[7].country.currency",
                                                Matchers.equalTo(countries.get(4)[1])))
                                .andExpect(jsonPath("$[7].country.population",
                                                Matchers.equalTo(countries.get(4)[2])))
                                .andExpect(jsonPath("$[7].country.latitude",
                                                Matchers.equalTo(countries.get(4)[3])))
                                .andExpect(jsonPath("$[7].country.longitude",
                                                Matchers.equalTo(countries.get(4)[4])))

                                .andExpect(jsonPath("$[8].cityId", Matchers.equalTo(9)))
                                .andExpect(jsonPath("$[8].cityName", Matchers.equalTo(cities.get(9)[0])))
                                .andExpect(jsonPath("$[8].population", Matchers.equalTo(cities.get(9)[1])))
                                .andExpect(jsonPath("$[8].latitude", Matchers.equalTo(cities.get(9)[2])))
                                .andExpect(jsonPath("$[8].longitude", Matchers.equalTo(cities.get(9)[3])))
                                .andExpect(jsonPath("$[8].country.countryId",
                                                Matchers.equalTo(cities.get(9)[4])))
                                .andExpect(jsonPath("$[8].country.countryName",
                                                Matchers.equalTo(countries.get(7)[0])))
                                .andExpect(jsonPath("$[8].country.currency",
                                                Matchers.equalTo(countries.get(7)[1])))
                                .andExpect(jsonPath("$[8].country.population",
                                                Matchers.equalTo(countries.get(7)[2])))
                                .andExpect(jsonPath("$[8].country.latitude",
                                                Matchers.equalTo(countries.get(7)[3])))
                                .andExpect(jsonPath("$[8].country.longitude",
                                                Matchers.equalTo(countries.get(7)[4])))

                                .andExpect(jsonPath("$[9].cityId", Matchers.equalTo(10)))
                                .andExpect(jsonPath("$[9].cityName", Matchers.equalTo(cities.get(10)[0])))
                                .andExpect(jsonPath("$[9].population", Matchers.equalTo(cities.get(10)[1])))
                                .andExpect(jsonPath("$[9].latitude", Matchers.equalTo(cities.get(10)[2])))
                                .andExpect(jsonPath("$[9].longitude", Matchers.equalTo(cities.get(10)[3])))
                                .andExpect(jsonPath("$[9].country.countryId",
                                                Matchers.equalTo(cities.get(10)[4])))
                                .andExpect(jsonPath("$[9].country.countryName",
                                                Matchers.equalTo(countries.get(7)[0])))
                                .andExpect(jsonPath("$[9].country.currency",
                                                Matchers.equalTo(countries.get(7)[1])))
                                .andExpect(jsonPath("$[9].country.population",
                                                Matchers.equalTo(countries.get(7)[2])))
                                .andExpect(jsonPath("$[9].country.latitude",
                                                Matchers.equalTo(countries.get(7)[3])))
                                .andExpect(jsonPath("$[9].country.longitude",
                                                Matchers.equalTo(countries.get(7)[4])));
        }

        @Test
        @Order(15)
        public void testGetCityNotFound() throws Exception {
                mockMvc.perform(get("/countries/cities/48")).andExpect(status().isNotFound());
        }

        @Test
        @Order(16)
        public void testGetCityById() throws Exception {
                mockMvc.perform(get("/countries/cities/1")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.cityId", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$.cityName", Matchers.equalTo(cities.get(1)[0])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(cities.get(1)[1])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(cities.get(1)[2])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(cities.get(1)[3])))
                                .andExpect(jsonPath("$.country.countryId",
                                                Matchers.equalTo(cities.get(1)[4])))
                                .andExpect(jsonPath("$.country.countryName",
                                                Matchers.equalTo(countries.get(1)[0])))
                                .andExpect(jsonPath("$.country.currency",
                                                Matchers.equalTo(countries.get(1)[1])))
                                .andExpect(jsonPath("$.country.population",
                                                Matchers.equalTo(countries.get(1)[2])))
                                .andExpect(jsonPath("$.country.latitude",
                                                Matchers.equalTo(countries.get(1)[3])))
                                .andExpect(jsonPath("$.country.longitude",
                                                Matchers.equalTo(countries.get(1)[4])));

                mockMvc.perform(get("/countries/cities/2")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.cityId", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$.cityName", Matchers.equalTo(cities.get(2)[0])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(cities.get(2)[1])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(cities.get(2)[2])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(cities.get(2)[3])))
                                .andExpect(jsonPath("$.country.countryId",
                                                Matchers.equalTo(cities.get(2)[4])))
                                .andExpect(jsonPath("$.country.countryName",
                                                Matchers.equalTo(countries.get(1)[0])))
                                .andExpect(jsonPath("$.country.currency",
                                                Matchers.equalTo(countries.get(1)[1])))
                                .andExpect(jsonPath("$.country.population",
                                                Matchers.equalTo(countries.get(1)[2])))
                                .andExpect(jsonPath("$.country.latitude",
                                                Matchers.equalTo(countries.get(1)[3])))
                                .andExpect(jsonPath("$.country.longitude",
                                                Matchers.equalTo(countries.get(1)[4])));

                mockMvc.perform(get("/countries/cities/3")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.cityId", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$.cityName", Matchers.equalTo(cities.get(3)[0])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(cities.get(3)[1])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(cities.get(3)[2])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(cities.get(3)[3])))
                                .andExpect(jsonPath("$.country.countryId",
                                                Matchers.equalTo(cities.get(3)[4])))
                                .andExpect(jsonPath("$.country.countryName",
                                                Matchers.equalTo(countries.get(2)[0])))
                                .andExpect(jsonPath("$.country.currency",
                                                Matchers.equalTo(countries.get(2)[1])))
                                .andExpect(jsonPath("$.country.population",
                                                Matchers.equalTo(countries.get(2)[2])))
                                .andExpect(jsonPath("$.country.latitude",
                                                Matchers.equalTo(countries.get(2)[3])))
                                .andExpect(jsonPath("$.country.longitude",
                                                Matchers.equalTo(countries.get(2)[4])));

                mockMvc.perform(get("/countries/cities/4")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.cityId", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.cityName", Matchers.equalTo(cities.get(4)[0])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(cities.get(4)[1])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(cities.get(4)[2])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(cities.get(4)[3])))
                                .andExpect(jsonPath("$.country.countryId",
                                                Matchers.equalTo(cities.get(4)[4])))
                                .andExpect(jsonPath("$.country.countryName",
                                                Matchers.equalTo(countries.get(2)[0])))
                                .andExpect(jsonPath("$.country.currency",
                                                Matchers.equalTo(countries.get(2)[1])))
                                .andExpect(jsonPath("$.country.population",
                                                Matchers.equalTo(countries.get(2)[2])))
                                .andExpect(jsonPath("$.country.latitude",
                                                Matchers.equalTo(countries.get(2)[3])))
                                .andExpect(jsonPath("$.country.longitude",
                                                Matchers.equalTo(countries.get(2)[4])));

                mockMvc.perform(get("/countries/cities/5")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.cityId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$.cityName", Matchers.equalTo(cities.get(5)[0])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(cities.get(5)[1])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(cities.get(5)[2])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(cities.get(5)[3])))
                                .andExpect(jsonPath("$.country.countryId",
                                                Matchers.equalTo(cities.get(5)[4])))
                                .andExpect(jsonPath("$.country.countryName",
                                                Matchers.equalTo(countries.get(3)[0])))
                                .andExpect(jsonPath("$.country.currency",
                                                Matchers.equalTo(countries.get(3)[1])))
                                .andExpect(jsonPath("$.country.population",
                                                Matchers.equalTo(countries.get(3)[2])))
                                .andExpect(jsonPath("$.country.latitude",
                                                Matchers.equalTo(countries.get(3)[3])))
                                .andExpect(jsonPath("$.country.longitude",
                                                Matchers.equalTo(countries.get(3)[4])));

                mockMvc.perform(get("/countries/cities/6")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.cityId", Matchers.equalTo(6)))
                                .andExpect(jsonPath("$.cityName", Matchers.equalTo(cities.get(6)[0])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(cities.get(6)[1])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(cities.get(6)[2])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(cities.get(6)[3])))
                                .andExpect(jsonPath("$.country.countryId",
                                                Matchers.equalTo(cities.get(6)[4])))
                                .andExpect(jsonPath("$.country.countryName",
                                                Matchers.equalTo(countries.get(3)[0])))
                                .andExpect(jsonPath("$.country.currency",
                                                Matchers.equalTo(countries.get(3)[1])))
                                .andExpect(jsonPath("$.country.population",
                                                Matchers.equalTo(countries.get(3)[2])))
                                .andExpect(jsonPath("$.country.latitude",
                                                Matchers.equalTo(countries.get(3)[3])))
                                .andExpect(jsonPath("$.country.longitude",
                                                Matchers.equalTo(countries.get(3)[4])));

                mockMvc.perform(get("/countries/cities/7")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.cityId", Matchers.equalTo(7)))
                                .andExpect(jsonPath("$.cityName", Matchers.equalTo(cities.get(7)[0])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(cities.get(7)[1])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(cities.get(7)[2])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(cities.get(7)[3])))
                                .andExpect(jsonPath("$.country.countryId",
                                                Matchers.equalTo(cities.get(7)[4])))
                                .andExpect(jsonPath("$.country.countryName",
                                                Matchers.equalTo(countries.get(4)[0])))
                                .andExpect(jsonPath("$.country.currency",
                                                Matchers.equalTo(countries.get(4)[1])))
                                .andExpect(jsonPath("$.country.population",
                                                Matchers.equalTo(countries.get(4)[2])))
                                .andExpect(jsonPath("$.country.latitude",
                                                Matchers.equalTo(countries.get(4)[3])))
                                .andExpect(jsonPath("$.country.longitude",
                                                Matchers.equalTo(countries.get(4)[4])));

                mockMvc.perform(get("/countries/cities/8")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.cityId", Matchers.equalTo(8)))
                                .andExpect(jsonPath("$.cityName", Matchers.equalTo(cities.get(8)[0])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(cities.get(8)[1])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(cities.get(8)[2])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(cities.get(8)[3])))
                                .andExpect(jsonPath("$.country.countryId",
                                                Matchers.equalTo(cities.get(8)[4])))
                                .andExpect(jsonPath("$.country.countryName",
                                                Matchers.equalTo(countries.get(4)[0])))
                                .andExpect(jsonPath("$.country.currency",
                                                Matchers.equalTo(countries.get(4)[1])))
                                .andExpect(jsonPath("$.country.population",
                                                Matchers.equalTo(countries.get(4)[2])))
                                .andExpect(jsonPath("$.country.latitude",
                                                Matchers.equalTo(countries.get(4)[3])))
                                .andExpect(jsonPath("$.country.longitude",
                                                Matchers.equalTo(countries.get(4)[4])));

                mockMvc.perform(get("/countries/cities/9")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.cityId", Matchers.equalTo(9)))
                                .andExpect(jsonPath("$.cityName", Matchers.equalTo(cities.get(9)[0])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(cities.get(9)[1])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(cities.get(9)[2])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(cities.get(9)[3])))
                                .andExpect(jsonPath("$.country.countryId",
                                                Matchers.equalTo(cities.get(9)[4])))
                                .andExpect(jsonPath("$.country.countryName",
                                                Matchers.equalTo(countries.get(7)[0])))
                                .andExpect(jsonPath("$.country.currency",
                                                Matchers.equalTo(countries.get(7)[1])))
                                .andExpect(jsonPath("$.country.population",
                                                Matchers.equalTo(countries.get(7)[2])))
                                .andExpect(jsonPath("$.country.latitude",
                                                Matchers.equalTo(countries.get(7)[3])))
                                .andExpect(jsonPath("$.country.longitude",
                                                Matchers.equalTo(countries.get(7)[4])));

                mockMvc.perform(get("/countries/cities/10")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.cityId", Matchers.equalTo(10)))
                                .andExpect(jsonPath("$.cityName", Matchers.equalTo(cities.get(10)[0])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(cities.get(10)[1])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(cities.get(10)[2])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(cities.get(10)[3])))
                                .andExpect(jsonPath("$.country.countryId",
                                                Matchers.equalTo(cities.get(10)[4])))
                                .andExpect(jsonPath("$.country.countryName",
                                                Matchers.equalTo(countries.get(7)[0])))
                                .andExpect(jsonPath("$.country.currency",
                                                Matchers.equalTo(countries.get(7)[1])))
                                .andExpect(jsonPath("$.country.population",
                                                Matchers.equalTo(countries.get(7)[2])))
                                .andExpect(jsonPath("$.country.latitude",
                                                Matchers.equalTo(countries.get(7)[3])))
                                .andExpect(jsonPath("$.country.longitude",
                                                Matchers.equalTo(countries.get(7)[4])));
        }

        @Test
        @Order(17)
        public void testPostCity() throws Exception {
                String content = "{\"cityName\": \"" + cities.get(11)[0] + "\", \"population\": " + cities.get(11)[1]
                                + ", \"latitude\": \"" + cities.get(11)[2] + "\", \"longitude\": \"" + cities.get(11)[3]
                                + "\", \"country\": {\"countryId\": " + cities.get(11)[4] + " }}";

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/countries/cities")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.cityId", Matchers.equalTo(11)))
                                .andExpect(jsonPath("$.cityName", Matchers.equalTo(cities.get(11)[0])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(cities.get(11)[1])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(cities.get(11)[2])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(cities.get(11)[3])))
                                .andExpect(jsonPath("$.country.countryId",
                                                Matchers.equalTo(cities.get(11)[4])))
                                .andExpect(jsonPath("$.country.countryName",
                                                Matchers.equalTo(countries.get(1)[0])))
                                .andExpect(jsonPath("$.country.currency",
                                                Matchers.equalTo(countries.get(1)[1])))
                                .andExpect(jsonPath("$.country.population",
                                                Matchers.equalTo(countries.get(1)[2])))
                                .andExpect(jsonPath("$.country.latitude",
                                                Matchers.equalTo(countries.get(1)[3])))
                                .andExpect(jsonPath("$.country.longitude",
                                                Matchers.equalTo(countries.get(1)[4])));
        }

        @Test
        @Order(18)
        public void testAfterPostCity() throws Exception {
                mockMvc.perform(get("/countries/cities/11")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.cityId", Matchers.equalTo(11)))
                                .andExpect(jsonPath("$.cityName", Matchers.equalTo(cities.get(11)[0])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(cities.get(11)[1])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(cities.get(11)[2])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(cities.get(11)[3])))
                                .andExpect(jsonPath("$.country.countryId",
                                                Matchers.equalTo(cities.get(11)[4])))
                                .andExpect(jsonPath("$.country.countryName",
                                                Matchers.equalTo(countries.get(1)[0])))
                                .andExpect(jsonPath("$.country.currency",
                                                Matchers.equalTo(countries.get(1)[1])))
                                .andExpect(jsonPath("$.country.population",
                                                Matchers.equalTo(countries.get(1)[2])))
                                .andExpect(jsonPath("$.country.latitude",
                                                Matchers.equalTo(countries.get(1)[3])))
                                .andExpect(jsonPath("$.country.longitude",
                                                Matchers.equalTo(countries.get(1)[4])));
        }

        @Test
        @Order(19)
        public void testDbAfterPostCity() throws Exception {
                City city = cityJpaRepository.findById(11).get();

                assertEquals(city.getCityId(), 11);
                assertEquals(city.getCityName(), cities.get(11)[0]);
                assertEquals(city.getPopulation(), Long.parseLong(Integer.toString((int) cities.get(11)[1])));
                assertEquals(city.getLatitude(), cities.get(11)[2]);
                assertEquals(city.getLongitude(), cities.get(11)[3]);
                assertEquals(city.getCountry().getCountryId(), cities.get(11)[4]);
                assertEquals(city.getCountry().getCountryName(), countries.get(1)[0]);
                assertEquals(city.getCountry().getCurrency(), countries.get(1)[1]);
                assertEquals(city.getCountry().getPopulation(),
                                Long.parseLong(Integer.toString((int) countries.get(1)[2])));
                assertEquals(city.getCountry().getLatitude(), countries.get(1)[3]);
                assertEquals(city.getCountry().getLongitude(), countries.get(1)[4]);
        }

        @Test
        @Order(20)
        public void testPutCityNotFound() throws Exception {
                String content = "{\"cityName\": \"" + cities.get(12)[0] + "\", \"population\": " + cities.get(12)[1]
                                + ", \"latitude\": \"" + cities.get(11)[2] + "\", \"longitude\": \"" + cities.get(11)[3]
                                + "\", \"country\": {\"countryId\": " + cities.get(11)[4] + " }}";

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/countries/cities/48")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest).andExpect(status().isNotFound());

        }

        @Test
        @Order(21)
        public void testPutCity() throws Exception {
                String content = "{\"cityName\": \"" + cities.get(12)[0] + "\", \"population\": " + cities.get(12)[1]
                                + ", \"latitude\": \"" + cities.get(12)[2] + "\", \"longitude\": \"" + cities.get(12)[3]
                                + "\", \"country\": {\"countryId\": " + cities.get(12)[4] + " }}";

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/countries/cities/4")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.cityId", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.cityName", Matchers.equalTo(cities.get(12)[0])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(cities.get(12)[1])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(cities.get(12)[2])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(cities.get(12)[3])))
                                .andExpect(jsonPath("$.country.countryId",
                                                Matchers.equalTo(cities.get(12)[4])))
                                .andExpect(jsonPath("$.country.countryName",
                                                Matchers.equalTo(countries.get(2)[0])))
                                .andExpect(jsonPath("$.country.currency",
                                                Matchers.equalTo(countries.get(2)[1])))
                                .andExpect(jsonPath("$.country.population",
                                                Matchers.equalTo(countries.get(2)[2])))
                                .andExpect(jsonPath("$.country.latitude",
                                                Matchers.equalTo(countries.get(2)[3])))
                                .andExpect(jsonPath("$.country.longitude",
                                                Matchers.equalTo(countries.get(2)[4])));

        }

        @Test
        @Order(22)
        public void testAfterPutCity() throws Exception {

                mockMvc.perform(get("/countries/cities/4")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.cityId", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.cityName", Matchers.equalTo(cities.get(12)[0])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(cities.get(12)[1])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(cities.get(12)[2])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(cities.get(12)[3])))
                                .andExpect(jsonPath("$.country.countryId",
                                                Matchers.equalTo(cities.get(12)[4])))
                                .andExpect(jsonPath("$.country.countryName",
                                                Matchers.equalTo(countries.get(2)[0])))
                                .andExpect(jsonPath("$.country.currency",
                                                Matchers.equalTo(countries.get(2)[1])))
                                .andExpect(jsonPath("$.country.population",
                                                Matchers.equalTo(countries.get(2)[2])))
                                .andExpect(jsonPath("$.country.latitude",
                                                Matchers.equalTo(countries.get(2)[3])))
                                .andExpect(jsonPath("$.country.longitude",
                                                Matchers.equalTo(countries.get(2)[4])));

        }

        @Test
        @Order(23)
        public void testDbAfterPutCity() throws Exception {
                City city = cityJpaRepository.findById(4).get();

                assertEquals(city.getCityId(), 4);
                assertEquals(city.getCityName(), cities.get(12)[0]);
                assertEquals(city.getPopulation(), Long.parseLong(Integer.toString((int) cities.get(12)[1])));
                assertEquals(city.getLatitude(), cities.get(12)[2]);
                assertEquals(city.getLongitude(), cities.get(12)[3]);
                assertEquals(city.getCountry().getCountryId(), cities.get(12)[4]);
                assertEquals(city.getCountry().getCountryName(), countries.get(2)[0]);
                assertEquals(city.getCountry().getCurrency(), countries.get(2)[1]);
                assertEquals(city.getCountry().getPopulation(),
                                Long.parseLong(Integer.toString((int) countries.get(2)[2])));
                assertEquals(city.getCountry().getLatitude(), countries.get(2)[3]);
                assertEquals(city.getCountry().getLongitude(), countries.get(2)[4]);
        }

        @Test
        @Order(24)
        public void testDeleteCityNotFound() throws Exception {

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/countries/cities/148")
                                .contentType(MediaType.APPLICATION_JSON);
                mockMvc.perform(mockRequest).andExpect(status().isNotFound());

        }

        @Test
        @Order(25)
        public void testDeleteCity() throws Exception {

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/countries/cities/11")
                                .contentType(MediaType.APPLICATION_JSON);
                mockMvc.perform(mockRequest).andExpect(status().isNoContent());
        }

        @Test
        @Order(26)
        public void testAfterDeleteCity() throws Exception {
                mockMvc.perform(get("/countries/cities")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", Matchers.hasSize(10)))

                                .andExpect(jsonPath("$[0].cityId", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$[0].cityName", Matchers.equalTo(cities.get(1)[0])))
                                .andExpect(jsonPath("$[0].population", Matchers.equalTo(cities.get(1)[1])))
                                .andExpect(jsonPath("$[0].latitude", Matchers.equalTo(cities.get(1)[2])))
                                .andExpect(jsonPath("$[0].longitude", Matchers.equalTo(cities.get(1)[3])))
                                .andExpect(jsonPath("$[0].country.countryId",
                                                Matchers.equalTo(cities.get(1)[4])))
                                .andExpect(jsonPath("$[0].country.countryName",
                                                Matchers.equalTo(countries.get(1)[0])))
                                .andExpect(jsonPath("$[0].country.currency",
                                                Matchers.equalTo(countries.get(1)[1])))
                                .andExpect(jsonPath("$[0].country.population",
                                                Matchers.equalTo(countries.get(1)[2])))
                                .andExpect(jsonPath("$[0].country.latitude",
                                                Matchers.equalTo(countries.get(1)[3])))
                                .andExpect(jsonPath("$[0].country.longitude",
                                                Matchers.equalTo(countries.get(1)[4])))

                                .andExpect(jsonPath("$[1].cityId", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$[1].cityName", Matchers.equalTo(cities.get(2)[0])))
                                .andExpect(jsonPath("$[1].population", Matchers.equalTo(cities.get(2)[1])))
                                .andExpect(jsonPath("$[1].latitude", Matchers.equalTo(cities.get(2)[2])))
                                .andExpect(jsonPath("$[1].longitude", Matchers.equalTo(cities.get(2)[3])))
                                .andExpect(jsonPath("$[1].country.countryId",
                                                Matchers.equalTo(cities.get(2)[4])))
                                .andExpect(jsonPath("$[1].country.countryName",
                                                Matchers.equalTo(countries.get(1)[0])))
                                .andExpect(jsonPath("$[1].country.currency",
                                                Matchers.equalTo(countries.get(1)[1])))
                                .andExpect(jsonPath("$[1].country.population",
                                                Matchers.equalTo(countries.get(1)[2])))
                                .andExpect(jsonPath("$[1].country.latitude",
                                                Matchers.equalTo(countries.get(1)[3])))
                                .andExpect(jsonPath("$[1].country.longitude",
                                                Matchers.equalTo(countries.get(1)[4])))

                                .andExpect(jsonPath("$[2].cityId", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$[2].cityName", Matchers.equalTo(cities.get(3)[0])))
                                .andExpect(jsonPath("$[2].population", Matchers.equalTo(cities.get(3)[1])))
                                .andExpect(jsonPath("$[2].latitude", Matchers.equalTo(cities.get(3)[2])))
                                .andExpect(jsonPath("$[2].longitude", Matchers.equalTo(cities.get(3)[3])))
                                .andExpect(jsonPath("$[2].country.countryId",
                                                Matchers.equalTo(cities.get(3)[4])))
                                .andExpect(jsonPath("$[2].country.countryName",
                                                Matchers.equalTo(countries.get(2)[0])))
                                .andExpect(jsonPath("$[2].country.currency",
                                                Matchers.equalTo(countries.get(2)[1])))
                                .andExpect(jsonPath("$[2].country.population",
                                                Matchers.equalTo(countries.get(2)[2])))
                                .andExpect(jsonPath("$[2].country.latitude",
                                                Matchers.equalTo(countries.get(2)[3])))
                                .andExpect(jsonPath("$[2].country.longitude",
                                                Matchers.equalTo(countries.get(2)[4])))

                                .andExpect(jsonPath("$[3].cityId", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$[3].cityName", Matchers.equalTo(cities.get(12)[0])))
                                .andExpect(jsonPath("$[3].population", Matchers.equalTo(cities.get(12)[1])))
                                .andExpect(jsonPath("$[3].latitude", Matchers.equalTo(cities.get(12)[2])))
                                .andExpect(jsonPath("$[3].longitude", Matchers.equalTo(cities.get(12)[3])))
                                .andExpect(jsonPath("$[3].country.countryId",
                                                Matchers.equalTo(cities.get(12)[4])))
                                .andExpect(jsonPath("$[3].country.countryName",
                                                Matchers.equalTo(countries.get(2)[0])))
                                .andExpect(jsonPath("$[3].country.currency",
                                                Matchers.equalTo(countries.get(2)[1])))
                                .andExpect(jsonPath("$[3].country.population",
                                                Matchers.equalTo(countries.get(2)[2])))
                                .andExpect(jsonPath("$[3].country.latitude",
                                                Matchers.equalTo(countries.get(2)[3])))
                                .andExpect(jsonPath("$[3].country.longitude",
                                                Matchers.equalTo(countries.get(2)[4])))

                                .andExpect(jsonPath("$[4].cityId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$[4].cityName", Matchers.equalTo(cities.get(5)[0])))
                                .andExpect(jsonPath("$[4].population", Matchers.equalTo(cities.get(5)[1])))
                                .andExpect(jsonPath("$[4].latitude", Matchers.equalTo(cities.get(5)[2])))
                                .andExpect(jsonPath("$[4].longitude", Matchers.equalTo(cities.get(5)[3])))
                                .andExpect(jsonPath("$[4].country.countryId",
                                                Matchers.equalTo(cities.get(5)[4])))
                                .andExpect(jsonPath("$[4].country.countryName",
                                                Matchers.equalTo(countries.get(3)[0])))
                                .andExpect(jsonPath("$[4].country.currency",
                                                Matchers.equalTo(countries.get(3)[1])))
                                .andExpect(jsonPath("$[4].country.population",
                                                Matchers.equalTo(countries.get(3)[2])))
                                .andExpect(jsonPath("$[4].country.latitude",
                                                Matchers.equalTo(countries.get(3)[3])))
                                .andExpect(jsonPath("$[4].country.longitude",
                                                Matchers.equalTo(countries.get(3)[4])))

                                .andExpect(jsonPath("$[5].cityId", Matchers.equalTo(6)))
                                .andExpect(jsonPath("$[5].cityName", Matchers.equalTo(cities.get(6)[0])))
                                .andExpect(jsonPath("$[5].population", Matchers.equalTo(cities.get(6)[1])))
                                .andExpect(jsonPath("$[5].latitude", Matchers.equalTo(cities.get(6)[2])))
                                .andExpect(jsonPath("$[5].longitude", Matchers.equalTo(cities.get(6)[3])))
                                .andExpect(jsonPath("$[5].country.countryId",
                                                Matchers.equalTo(cities.get(6)[4])))
                                .andExpect(jsonPath("$[5].country.countryName",
                                                Matchers.equalTo(countries.get(3)[0])))
                                .andExpect(jsonPath("$[5].country.currency",
                                                Matchers.equalTo(countries.get(3)[1])))
                                .andExpect(jsonPath("$[5].country.population",
                                                Matchers.equalTo(countries.get(3)[2])))
                                .andExpect(jsonPath("$[5].country.latitude",
                                                Matchers.equalTo(countries.get(3)[3])))
                                .andExpect(jsonPath("$[5].country.longitude",
                                                Matchers.equalTo(countries.get(3)[4])))

                                .andExpect(jsonPath("$[6].cityId", Matchers.equalTo(7)))
                                .andExpect(jsonPath("$[6].cityName", Matchers.equalTo(cities.get(7)[0])))
                                .andExpect(jsonPath("$[6].population", Matchers.equalTo(cities.get(7)[1])))
                                .andExpect(jsonPath("$[6].latitude", Matchers.equalTo(cities.get(7)[2])))
                                .andExpect(jsonPath("$[6].longitude", Matchers.equalTo(cities.get(7)[3])))
                                .andExpect(jsonPath("$[6].country.countryId",
                                                Matchers.equalTo(cities.get(7)[4])))
                                .andExpect(jsonPath("$[6].country.countryName",
                                                Matchers.equalTo(countries.get(4)[0])))
                                .andExpect(jsonPath("$[6].country.currency",
                                                Matchers.equalTo(countries.get(4)[1])))
                                .andExpect(jsonPath("$[6].country.population",
                                                Matchers.equalTo(countries.get(4)[2])))
                                .andExpect(jsonPath("$[6].country.latitude",
                                                Matchers.equalTo(countries.get(4)[3])))
                                .andExpect(jsonPath("$[6].country.longitude",
                                                Matchers.equalTo(countries.get(4)[4])))

                                .andExpect(jsonPath("$[7].cityId", Matchers.equalTo(8)))
                                .andExpect(jsonPath("$[7].cityName", Matchers.equalTo(cities.get(8)[0])))
                                .andExpect(jsonPath("$[7].population", Matchers.equalTo(cities.get(8)[1])))
                                .andExpect(jsonPath("$[7].latitude", Matchers.equalTo(cities.get(8)[2])))
                                .andExpect(jsonPath("$[7].longitude", Matchers.equalTo(cities.get(8)[3])))
                                .andExpect(jsonPath("$[7].country.countryId",
                                                Matchers.equalTo(cities.get(8)[4])))
                                .andExpect(jsonPath("$[7].country.countryName",
                                                Matchers.equalTo(countries.get(4)[0])))
                                .andExpect(jsonPath("$[7].country.currency",
                                                Matchers.equalTo(countries.get(4)[1])))
                                .andExpect(jsonPath("$[7].country.population",
                                                Matchers.equalTo(countries.get(4)[2])))
                                .andExpect(jsonPath("$[7].country.latitude",
                                                Matchers.equalTo(countries.get(4)[3])))
                                .andExpect(jsonPath("$[7].country.longitude",
                                                Matchers.equalTo(countries.get(4)[4])))

                                .andExpect(jsonPath("$[8].cityId", Matchers.equalTo(9)))
                                .andExpect(jsonPath("$[8].cityName", Matchers.equalTo(cities.get(9)[0])))
                                .andExpect(jsonPath("$[8].population", Matchers.equalTo(cities.get(9)[1])))
                                .andExpect(jsonPath("$[8].latitude", Matchers.equalTo(cities.get(9)[2])))
                                .andExpect(jsonPath("$[8].longitude", Matchers.equalTo(cities.get(9)[3])))
                                .andExpect(jsonPath("$[8].country.countryId",
                                                Matchers.equalTo(cities.get(9)[4])))
                                .andExpect(jsonPath("$[8].country.countryName",
                                                Matchers.equalTo(countries.get(7)[0])))
                                .andExpect(jsonPath("$[8].country.currency",
                                                Matchers.equalTo(countries.get(7)[1])))
                                .andExpect(jsonPath("$[8].country.population",
                                                Matchers.equalTo(countries.get(7)[2])))
                                .andExpect(jsonPath("$[8].country.latitude",
                                                Matchers.equalTo(countries.get(7)[3])))
                                .andExpect(jsonPath("$[8].country.longitude",
                                                Matchers.equalTo(countries.get(7)[4])))

                                .andExpect(jsonPath("$[9].cityId", Matchers.equalTo(10)))
                                .andExpect(jsonPath("$[9].cityName", Matchers.equalTo(cities.get(10)[0])))
                                .andExpect(jsonPath("$[9].population", Matchers.equalTo(cities.get(10)[1])))
                                .andExpect(jsonPath("$[9].latitude", Matchers.equalTo(cities.get(10)[2])))
                                .andExpect(jsonPath("$[9].longitude", Matchers.equalTo(cities.get(10)[3])))
                                .andExpect(jsonPath("$[9].country.countryId",
                                                Matchers.equalTo(cities.get(10)[4])))
                                .andExpect(jsonPath("$[9].country.countryName",
                                                Matchers.equalTo(countries.get(7)[0])))
                                .andExpect(jsonPath("$[9].country.currency",
                                                Matchers.equalTo(countries.get(7)[1])))
                                .andExpect(jsonPath("$[9].country.population",
                                                Matchers.equalTo(countries.get(7)[2])))
                                .andExpect(jsonPath("$[9].country.latitude",
                                                Matchers.equalTo(countries.get(7)[3])))
                                .andExpect(jsonPath("$[9].country.longitude",
                                                Matchers.equalTo(countries.get(7)[4])));
        }

        @Test
        @Order(27)
        public void testGetCountryByCityId() throws Exception {
                mockMvc.perform(get("/cities/1/country")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(1)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(1)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(1)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(1)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(1)[4])));

                mockMvc.perform(get("/cities/2/country")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(1)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(1)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(1)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(1)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(1)[4])));

                mockMvc.perform(get("/cities/3/country")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(2)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(2)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(2)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(2)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(2)[4])));

                mockMvc.perform(get("/cities/4/country")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(2)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(2)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(2)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(2)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(2)[4])));

                mockMvc.perform(get("/cities/5/country")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(3)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(3)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(3)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(3)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(3)[4])));

                mockMvc.perform(get("/cities/6/country")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(3)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(3)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(3)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(3)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(3)[4])));

                mockMvc.perform(get("/cities/7/country")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(4)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(4)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(4)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(4)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(4)[4])));

                mockMvc.perform(get("/cities/8/country")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(4)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(4)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(4)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(4)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(4)[4])));

                mockMvc.perform(get("/cities/9/country")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(7)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(7)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(7)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(7)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(7)[4])));

                mockMvc.perform(get("/cities/10/country")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.countryId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$.countryName", Matchers.equalTo(countries.get(7)[0])))
                                .andExpect(jsonPath("$.currency", Matchers.equalTo(countries.get(7)[1])))
                                .andExpect(jsonPath("$.population", Matchers.equalTo(countries.get(7)[2])))
                                .andExpect(jsonPath("$.latitude", Matchers.equalTo(countries.get(7)[3])))
                                .andExpect(jsonPath("$.longitude", Matchers.equalTo(countries.get(7)[4])));
        }

        @AfterAll
        public void cleanup() {
                jdbcTemplate.execute("drop table city");
                jdbcTemplate.execute("drop table country");
        }

}
