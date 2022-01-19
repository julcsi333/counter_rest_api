# counter_rest_api

## Application

The Counter app can be launched with `gradlew bootRun` in the root of the repository.

The application is available at http://localhost:8080
It has three endpoints:
- `/count` : A GET http request without body to http://localhost:8080/count gives back the counter's state as `{"value": 10}`. It's initially 0.
- `/count/add` : A POST http request with a Counter JSON object body `{"value": 5}` to http://localhost:8080/count/add adds the object's value to the counter.
- `/count/subtract` : A POST http request with a Counter JSON object body `{"value": 10}` to http://localhost:8080/count/subtract subtracts the object's value from the counter.

## OpenAPI
I have also added an OpenAPI which can be used to test the application. It is available at http://localhost:8080/swagger-ui/index.html#/

## Tests
The tests can be run with `gradlew test` command in the root of the repository.


