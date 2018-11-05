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

import ca.com.diogo.domain.Car;
import ca.com.diogo.domain.CarService;
import ca.com.diogo.domain.Response;
import ca.com.diogo.util.InitConnection;

@Path("/cars")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class CarResources {

	Connection conn = null;
	String url = null;

	private CarService carService = new CarService();

	public CarResources() throws ServletException {
		new InitConnection();
	}

	@GET
	public List<Car> get() throws ServletException {
		new InitConnection();
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

	private Car getCarFromRequest(HttpServletRequest request) {
		// CarService carService = new CarService();
		// create the car
		Car car = new Car();
		// get the id from the request
		String id = request.getParameter("id");
		// if the id was informed in the request
		if (id != null) {
			// if the id was informed, gets it from the database
			car = this.carService.getCar(Long.parseLong(id));
		}

		car.setName(request.getParameter("name"));
		car.setDesc(request.getParameter("description"));
		car.setUrlPhoto(request.getParameter("url_photo"));
		car.setUrlVideo(request.getParameter("url_video"));
		car.setLatitude(request.getParameter("latitude"));
		car.setLongitude(request.getParameter("longitude"));
		car.setType(request.getParameter("type"));
		return car;
	}
}
