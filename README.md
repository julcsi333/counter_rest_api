# counter_rest_api
The application is available on port 8080
It has three endpoints:
- /count
- /count/add
- /count/subtract

A GET http request without body to http://localhost:8080/count gives back the counter's state as `{"value": 10}`. It's initially 0.

A POST http request with a Counter JSON object body `{"value": 5}` to http://localhost:8080/count/add adds the object's value to the counter.

A POST http request with a Counter JSON object body `{"value": 10}` to http://localhost:8080/count/subtract subtracts the object's value from the counter.
