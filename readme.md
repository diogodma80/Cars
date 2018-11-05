App Engine application using Restful web services implemented with the Servlet API.
At this moment there is no UI.

Get all the cars
GET http://localhost:8080/cars
----------
Get the car id of 1
GET http://localhost:8080/cars/1
----------
Post to save a new car
POST http://localhost:8080/cars/cars

Request body
key:name/value:Lamborghini Diablo
key:type/value:esportivo

Response body
{
    "id": 31,
    "type": "esportivo",
    "name": "Lamborghini Diablo"
}
----------
Delete a car from database
DELETE http://localhost:8080/cars/1

{
    "status": "OK",
    "msg": "The car was deleted successfully"
}
----------
