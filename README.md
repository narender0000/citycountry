In this project, we will develop a Spring Boot application to provide a REST service for the 'GeoHub' application. With the 'GeoHub' application, users can fetch information about cities belonging to specific countries.

For this application, we need to represent two entities: `City` and `Country`. The `City` entity maintains a Many-to-One relationship with the `Country` entity.

<details>
<summary>**Implementation Files**</summary>

Use these files to complete the implementation:

- `CityController.java`
- `CityRepository.java`
- `CityJpaService.java`
- `CityJpaRepository.java`
- `City.java`
- `CountryController.java`
- `CountryRepository.java`
- `CountryJpaService.java`
- `CountryJpaRepository.java`
- `Country.java`

</details>

Create a database that contains two tables `country` and `city` using the given database schema.

You can refer to this [session](https://learning.ccbp.in/course?c_id=e345dfa4-f5ce-406e-b19a-4ed720c54136&s_id=6a60610e-79c2-4e15-b675-45ddbd9bbe82&t_id=f880166e-2f51-4403-81a0-d2430694dae8), for creating a database.

_Create the SQL files and compose accurate queries to run the application. Inaccurate SQL files will result in test case failures._

<details>
<summary>**Database Schema**</summary>

#### Country Table

|   Columns   |                 Type                  |
| :---------: | :-----------------------------------: |
|  countryId  | INTEGER (Primary Key, Auto Increment) |
| countryName |                 TEXT                  |
|  currency   |                 TEXT                  |
| population  |                INTEGER                |
|  latitude   |                 TEXT                  |
|  longitude  |                 TEXT                  |

#### City Table

|  Columns   |                 Type                 |
| :--------: | :----------------------------------: |
|   cityId   | INTEGER(Primary Key, Auto Increment) |
|  cityName  |                 TEXT                 |
| population |               INTEGER                |
|  latitude  |                 TEXT                 |
| longitude  |                 TEXT                 |
| countryId  |        INTEGER (Foreign Key)         |

You can use the given sample data to populate the tables.

<details>
<summary>**Sample Data**</summary>

#### Country Data

| countryId |  country  | currency | population |  latitude  |  longitude  |
| :-------: | :-------: | :------: | :--------: | :--------: | :---------: |
|     1     |   India   |   INR    | 1393409038 | 20.5937° N | 78.9629° E  |
|     2     |    USA    |   USD    | 331893745  | 37.7749° N | 122.4194° W |
|     3     | Australia |   AUD    |  25687041  | 25.2744° S | 133.7751° E |
|     4     |  Canada   |   CAD    |  38008005  | 56.1304° N | 106.3468° W |
|     5     |    UK     |   GBP    |  68207116  | 51.5074° N |  0.1278° W  |

#### City Data

|    city    | population |   latitude   |  longitude   | countryId |
| :--------: | :--------: | :----------: | :----------: | :-------: |
|   Mumbai   |  20185064  |  19.0760° N  |  72.8777° E  |     1     |
| Bangalore  |  12425304  |  12.9716° N  |  77.5946° E  |     1     |
|  New York  |  8419600   |  40.7128° N  |  74.0060° W  |     2     |
|  Chicago   |  2716000   |  41.8781° N  |  87.6298° W  |     2     |
|   Sydney   |  5303000   |  33.8688° S  | 151.2093° E  |     3     |
| Melbourne  |  5084000   |  37.8136° S  | 144.9631° E  |     3     |
| Vancouver  |   675218   |  49.2827° N  | 123.1207° W  |     4     |
|  Toronto   |  3140000   | 43.651070° N | 79.347015° W |     4     |
|   London   |  8982000   |  51.5074° N  |  0.1278° W   |     5     |
| Manchester |   547627   |  53.4808° N  |  2.2426° W   |     5     |

</details>

</details>

<MultiLineNote>

Use only `city` and `country` as the table names in your code.

</MultiLineNote>

### Completion Instructions

- `City.java`: The `City` class should contain the following attributes.

    | Attribute  |  Type   |
    | :--------: | :-----: |
    |   cityId   |   int   |
    |  cityName  | String  |
    | population |  long   |
    |  latitude  | String  |
    | longitude  | String  |
    |  country   | Country |

- `CityRepository.java`: Create an `interface` containing the required methods.
- `CityJpaService.java`: Update the service class with logic for managing city data.
- `CityController.java`: Create the controller class to handle HTTP requests.
- `CityJpaRepository.java`: Create an interface that implements the `JpaRepository` interface.
  
- `Country.java`: The `Country` class should contain the following attributes.

    |  Attribute  |  Type  |
    | :---------: | :----: |
    |  countryId  |  int   |
    | countryName | String |
    |  currency   | String |
    | population  |  long  |
    |  latitude   | String |
    |  longitude  | String |

- `CountryRepository.java`: Create an `interface` containing the required methods.
- `CountryJpaService.java`: Update the service class with logic for managing countries data.
- `CountryController.java`: Create the controller class to handle HTTP requests.
- `CountryJpaRepository.java`: Create an interface that implements the `JpaRepository` interface.

Implement the following APIs.

<details>
<summary>**API 1: GET /countries**</summary>

#### Path: `/countries`

#### Method: `GET`

#### Description:

Returns a list of all countries in the `country` table.

#### Response

```json
[
    {
        "countryId": 1,
        "countryName": "India",
        "currency": "INR",
        "population": 1393409038,
        "latitude": "20.5937° N",
        "longitude": "78.9629° E"
    },
    ...
]
```

</details>

<details>
<summary>**API 2: POST /countries**</summary>

#### Path: `/countries`

#### Method: `POST`

#### Description:

Creates a new country in the `country` table. The `countryId` is auto-incremented.

#### Request

```json
{
  "countryName": "Germany",
  "currency": "EUR",
  "population": 83166711,
  "latitude": "51.1657° N",
  "longitude": "10.4515° E"
}
```

#### Response

```json
{
    "countryId": 6,
    "countryName": "Germany",
    "currency": "EUR",
    "population": 83166711,
    "latitude": "51.1657° N",
    "longitude": "10.4515° E"
}
```

</details>

<details>
<summary>**API 3: GET /countries/{countryId}**</summary>

#### Path: `/countries/{countryId}`

#### Method: `GET`

#### Description:

Returns a country based on the `countryId`. If the given `countryId` is not found in the `country` table, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`.


#### Success Response

```json
{
    "countryId": 2,
    "countryName": "USA",
    "currency": "USD",
    "population": 331893745,
    "latitude": "37.7749° N",
    "longitude": "122.4194° W"
}
```

</details>

<details>
<summary>**API 4: PUT /countries/{countryId}**</summary>

#### Path: `/countries/{countryId}`

#### Method: `PUT`

#### Description:

Updates the details of a country based on the `countryId` and returns the updated country details. If the given `countryId` is not found in the `country` table, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`.

#### Request

```json
{
    "countryName": "United Kingdom"
}
```

#### Success Response

```json
{
    "countryId": 5,
    "countryName": "United Kingdom",
    "currency": "GBP",
    "population": 68207116,
    "latitude": "51.5074° N",
    "longitude": "0.1278° W"
}
```

</details>

<details>
<summary>**API 5: DELETE /countries/{countryId}**</summary>

#### Path: `/countries/{countryId}`

#### Method: `DELETE`

#### Description:

Deletes a country from the `country` table based on the `countryId` and returns the status code `204`(raise `ResponseStatusException` with `HttpStatus.NO_CONTENT`). If the given `countryId` is not found in the `country` table, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`. 

</details>

<details>
<summary>**API 6: GET /countries/cities**</summary>

#### Path: `/countries/cities`

#### Method: `GET`

#### Description:

Returns a list of all cities in the `city` table.

#### Response

```json
[
    {
        "cityId": 1,
        "cityName": "Mumbai",
        "population": 20185064,
        "latitude": "19.0760° N",
        "longitude": "72.8777° E",
        "country": {
            "countryId": 1,
            "countryName": "India",
            "currency": "INR",
            "population": 1393409038,
            "latitude": "20.5937° N",
            "longitude": "78.9629° E"
        }
    },
    ...
]
```

</details>

<details>
<summary>**API 7: POST /countries/cities**</summary>

#### Path: `/countries/cities`

#### Method: `POST`

#### Description:

Creates a new city in the `city` table and create an association between the city and the country based on the `countryId` of the `country` field. The `cityId` is auto-incremented.

#### Request

```json
{
    "cityName": "Delhi",
    "population": 30291000,
    "latitude": "28.7041° N",
    "longitude": "77.1025° E",
    "country": {
        "countryId": 1
    }
}
```

#### Response

```json
{
    "cityId": 11,
    "cityName": "Delhi",
    "population": 30291000,
    "latitude": "28.7041° N",
    "longitude": "77.1025° E",
    "country": {
        "countryId": 1,
        "countryName": "India",
        "currency": "INR",
        "population": 1393409038,
        "latitude": "20.5937° N",
        "longitude": "78.9629° E"
    }
}
```

</details>

<details>
<summary>**API 8: GET /countries/cities/{cityId}**</summary>

#### Path: `/countries/cities/{cityId}`

#### Method: `GET`

#### Description:

Returns a city based on the `cityId`. If the given `cityId` is not found in the `city` table, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`.


#### Success Response

```json
{
    "cityId": 2,
    "cityName": "Bangalore",
    "population": 12425304,
    "latitude": "12.9716° N",
    "longitude": "77.5946° E",
    "country": {
        "countryId": 1,
        "countryName": "India",
        "currency": "INR",
        "population": 1393409038,
        "latitude": "20.5937° N",
        "longitude": "78.9629° E"
    }
}
```

</details>

<details>
<summary>**API 9: PUT /countries/cities/{cityId}**</summary>

#### Path: `/countries/cities/{cityId}`

#### Method: `PUT`

#### Description:

Updates the details of a city based on the `cityId` and returns the updated city details. If the `countryId` in the `country` field is provided, update the association between the city and the country based on the `countryId`. If the given `cityId` is not found in the `city` table, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`.

#### Request

```json
{
    "cityName": "Los Angeles",
    "population": 3980400,
    "latitude": "34.0522° N",
    "longitude": "118.2437° W"
}
```

#### Success Response

```json
{
    "cityId": 4,
    "cityName": "Los Angeles",
    "population": 3980400,
    "latitude": "34.0522° N",
    "longitude": "118.2437° W",
    "country": {
        "countryId": 2,
        "countryName": "USA",
        "currency": "USD",
        "population": 331893745,
        "latitude": "37.7749° N",
        "longitude": "122.4194° W"
    }
}
```

</details>

<details>
<summary>**API 10: DELETE /countries/cities/{cityId}**</summary>

#### Path: `/countries/cities/{cityId}`

#### Method: `DELETE`

#### Description:

Deletes a city from the `city` table based on the `cityId` and returns the status code `204`(raise `ResponseStatusException` with `HttpStatus.NO_CONTENT`). If the given `cityId` is not found in the `city` table, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`.

</details>

<details>
<summary>**API 11: GET /cities/{cityId}/country**</summary>

#### Path: `/cities/{cityId}/country`

#### Method: `GET`

#### Description:

Returns a country of the city based on the `cityId`. If the given `cityId` is not found in the `city` table, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`.

#### Response

```json
{
    "countryId": 1,
    "countryName": "India",
    "currency": "INR",
    "population": 1393409038,
    "latitude": "20.5937° N",
    "longitude": "78.9629° E"
}
```

</details>

**Do not modify the code in `GeoHubApplication.java`**