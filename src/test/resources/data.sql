INSERT INTO country (countryName, currency, population, latitude, longitude)
SELECT 'India', 'INR', 1393409038, '20.5937° N', '78.9629° E'
WHERE NOT EXISTS(SELECT 1 FROM country WHERE countryId=1);

INSERT INTO country (countryName, currency, population, latitude, longitude)
SELECT 'USA', 'USD', 331893745, '37.7749° N', '122.4194° W'
WHERE NOT EXISTS(SELECT 2 FROM country WHERE countryId=2);

INSERT INTO country (countryName, currency, population, latitude, longitude)
SELECT 'Australia', 'AUD', 25687041, '25.2744° S', '133.7751° E'
WHERE NOT EXISTS(SELECT 3 FROM country WHERE countryId=3);

INSERT INTO country (countryName, currency, population, latitude, longitude)
SELECT 'Canada', 'CAD', 38008005, '56.1304° N', '106.3468° W'
WHERE NOT EXISTS(SELECT 4 FROM country WHERE countryId=4);

INSERT INTO country (countryName, currency, population, latitude, longitude)
SELECT 'UK', 'GBP', 68207116, '51.5074° N', '0.1278° W'
WHERE NOT EXISTS(SELECT 5 FROM country WHERE countryId=5);

INSERT INTO city (cityName, population, latitude, longitude, countryId)
SELECT 'Mumbai', 20185064, '19.0760° N', '72.8777° E', 1
WHERE NOT EXISTS(SELECT 1 FROM city WHERE cityId=1);

INSERT INTO city (cityName, population, latitude, longitude, countryId)
SELECT 'Bangalore', 12425304, '12.9716° N', '77.5946° E', 1
WHERE NOT EXISTS(SELECT 2 FROM city WHERE cityId=2);

INSERT INTO city (cityName, population, latitude, longitude, countryId)
SELECT 'New York', 8419600, '40.7128° N', '74.0060° W', 2
WHERE NOT EXISTS(SELECT 3 FROM city WHERE cityId=3);

INSERT INTO city (cityName, population, latitude, longitude, countryId)
SELECT 'Chicago', 2716000, '41.8781° N', '87.6298° W', 2
WHERE NOT EXISTS(SELECT 4 FROM city WHERE cityId=4);

INSERT INTO city (cityName, population, latitude, longitude, countryId)
SELECT 'Sydney', 5303000, '33.8688° S', '151.2093° E', 3
WHERE NOT EXISTS(SELECT 5 FROM city WHERE cityId=5);

INSERT INTO city (cityName, population, latitude, longitude, countryId)
SELECT 'Melbourne', 5084000, '37.8136° S', '144.9631° E', 3
WHERE NOT EXISTS(SELECT 6 FROM city WHERE cityId=6);

INSERT INTO city (cityName, population, latitude, longitude, countryId)
SELECT 'Vancouver', 675218, '49.2827° N', '123.1207° W', 4
WHERE NOT EXISTS(SELECT 7 FROM city WHERE cityId=7);

INSERT INTO city (cityName, population, latitude, longitude, countryId)
SELECT 'Toronto', 3140000, '43.651070° N', '79.347015° W', 4
WHERE NOT EXISTS(SELECT 8 FROM city WHERE cityId=8);

INSERT INTO city (cityName, population, latitude, longitude, countryId)
SELECT 'London', 8982000, '51.5074° N', '0.1278° W', 5
WHERE NOT EXISTS(SELECT 9 FROM city WHERE cityId=9);

INSERT INTO city (cityName, population, latitude, longitude, countryId)
SELECT 'Manchester', 547627, '53.4808° N', '2.2426° W', 5
WHERE NOT EXISTS(SELECT 10 FROM city WHERE cityId=10);