App Engine application using Restful web services implemented with the Java API and Jersey.
At this moment there is no UI for the web services.

Get all the cars
GET http://localhost:8080/rest/cars
----------
Get the car id of 1
GET http://localhost:8080/rest/cars/1
----------
Post to save a new car
POST http://localhost:8080/rest/cars
Header Content-Type:application/json

Request body
{
	"name":"Lamborghini Diablo",
	"type":"esportivo"
}

Response body
{
  "status": "OK",
  "msg": "Car saved successfully"
}
----------
Delete a car from database
DELETE http://localhost:8080/cars/1

{
  "status": "OK",
  "msg": "Car deleted successfully"
}
----------
