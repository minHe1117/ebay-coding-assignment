# Flexible Calculator

## Features
- RESTful APIs for basic arithmetic operations (addition, subtraction, multiplication, division)
- Supports multiple numeric types (integers, floating points, etc.)
- Allows chained operations on a single value
- Structured logging with Log4j2
- Graceful error handling with clear error messages

## Technology Stack
- Java: Core programming language
- Spring Boot 3.x: REST API framework
- JUnit 5: Testing framework
- Log4j2: Logging
- Mockito: For mocking in unit tests
- Maven: Build and dependency management

## API Endpoints
### Single Operation
**URL:** /v1/calculator/calculate
**Method:** POST
**Description:** Performs a single arithmetic operation

### Request
```shell
curl --location 'localhost:9090/v1/calculator/calculate' \
--header 'Content-Type: application/json' \
--data '{
        "op": "ADD",
        "num1": 1,
        "num2": 2
    }'
```
### Response

```json
{
  "code": "200",
  "message": "Calculation successful",
  "data": 3
}
```

### Chained Operations
**URL:** /v1/calculator/chain
**Method:** POST
**Description:** Performs a sequence of arithmetic operations on an initial value.

### Request
```shell
curl --location --request POST 'http://localhost:8080/v1/calculator/chain' \
--header 'Content-Type: application/json' \
--data-raw '{
  "payload": {
    "initial_value": 10,
    "chainOperations": [
      {
        "op": "ADD",
        "num": 5
      },
      {
        "op": "SUBTRACT",
        "num": 3
      },
      {
        "op": "MULTIPLY",
        "num": 2
      }
    ]
  }
}'
'
```
### Response

```json
{
  "code": "200",
  "message": "Calculation successful",
  "data": 24
}
```
## Error Handling Sample


```shell
curl --request POST \
  --url http://localhost:8081/v1/calculator/calculate \
  --header 'Content-Type: application/json' \
  --header 'id: 123' \
  --data '{
  "payload": {
    "op": "DIVIDE",
    "num1": 10,
    "num2": 0
  }
}'
```
### Response

```json
{
  "statusCode": "500",
  "message": "Division by zero is not allowed",
  "data": null,
  "success": false
}
```

## Future Improvements
- Support for more Operators and Numeric Types. Expand the calculator to handle more operations (like mod, square root) and support more numeric types .
- Add more validation like headers, etc,.
- Implement metrics like api response times and request volumes.
- Caching frequently used results for performance improvements.
- Designs like retries and multithreading


