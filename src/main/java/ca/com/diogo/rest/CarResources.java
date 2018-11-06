package ca.com.diogo.rest;

import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.com.diogo.domain.Car;
import ca.com.diogo.domain.CarService;
import ca.com.diogo.domain.Response;
import ca.com.diogo.util.InitConnection;

@Path("/cars")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class CarResources {

	//Connection conn = null;
	String url = null;

	@Autowired
	private CarService carService;
	
	@Autowired
	private InitConnection conn;

	public CarResources() throws ServletException {
		//connection is now @Autowired
		//new InitConnection();
	}

	@GET
	public List<Car> get() throws ServletException {
		//new InitConnection();
		List<Car> cars = carService.getCars();
		return cars;
	}

	@GET
	@Path("{id}")
	public Car get(@PathParam("id") long id) {
		Car car = carService.getCar(id);
		return car;
	}

	@GET
	@Path("/type/{type}")
	public List<Car> getByType(@PathParam("type") String type) {
		List<Car> cars = carService.findByType(type);
		return cars;
	}

	@GET
	@Path("/name/{name}")
	public List<Car> getByName(@PathParam("name") String name) {
		List<Car> cars = carService.findByName(name);
		return cars;
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		carService.delete(id);
		return Response.Ok("Car deleted successfully");
	}

	@POST
	public Response post(Car car) {
		carService.save(car);
		return Response.Ok("Car saved successfully");
	}

	@PUT
	public Response put(Car car) {
		carService.save(car);
		return Response.Ok("Car updates successfully");
	}
}
