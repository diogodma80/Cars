package ca.com.diogo.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ca.com.diogo.domain.BaseDao;
import ca.com.diogo.domain.Car;
import ca.com.diogo.domain.CarService;
import ca.com.diogo.domain.ListCars;
import ca.com.diogo.domain.Response;
import ca.com.diogo.util.RegexUtil;
import ca.com.diogo.util.ServletUtil;

/**
 * Servlet implementation class CarServlet
 */
@WebServlet("/cars/*")
public class CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection conn = null;
	String url = null;

	private CarService carService = new CarService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		initConnection();

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		String path = request.getRequestURI();
		if (path.startsWith("/favicon.ico")) {
			return; // ignore the request for favicon.ico
		}

		response.getWriter().write("Calling */cars/ or */cars/id to get the JSON \n");

		try {
			log("calling initConnection() on doGet()");
			initConnection();
			log("back to doGet()");
			String requestUri = request.getRequestURI();
			log("Requested URL: " + requestUri);
			Long id = RegexUtil.matchId(requestUri);

			if (id != null) {
				log("Request for a specific car/id: " + id);
				Car car = this.carService.getCar(id);
				if (car != null) {
					log("Found the car: " + car.getName());
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					String json = gson.toJson(car);
					ServletUtil.writeJSON(response, json);
				} else {
					response.sendError(404, "Car not found");
				}
			} else {
				log("Requested the list of cars");
				List<Car> cars = carService.getCars();
				ListCars list = new ListCars();
				list.setCars(cars);
				// using Gson
				log("setting gson to create the json output");
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(list);
				log("calling writeJSON()");
				ServletUtil.writeJSON(response, json);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					log("Closing the connection in finally block");
					conn.close();
					log("Connection is now closed");
				} catch (SQLException e) {
					log("An exception occurred when closing the connection");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		initConnection();
		// Create the car
		log("Into doPost");
		Car car = getCarFromRequest(request);

		// saves the car
		log("Saving the car after calling getCarFromRequest method");
		this.carService.save(car);

		// Writes the json of the new car saved
		log("Preparing the gson to display the newly saved car");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(car);
		ServletUtil.writeJSON(response, json);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//super.doDelete(request, response);

		initConnection();

		String requestUri = request.getRequestURI();

		Long id = RegexUtil.matchId(requestUri);

		if (id != null) {
			carService.delete(id);
			Response resp = Response.Ok("The car was deleted successfully");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(resp);
			ServletUtil.writeJSON(response, json);
		} else {
			// invalid URL
			response.sendError(404, "Invalid URL");
		}
	}

	private Car getCarFromRequest(HttpServletRequest request) {
		// CarService carService = new CarService();
		// create the car
		log("Into getCarFromRequest method");
		Car car = new Car();
		// get the id from the request
		log("Created the new car");
		String id = request.getParameter("id");
		log("Got the id parameter: " + id);
		// if the id was informed in the request
		if (id != null) {
			// if the id was informed, gets it from the database
			log("The id was informed. Getting it from the database");
			car = this.carService.getCar(Long.parseLong(id));
		}

		log("Updating the car settings with the reqest parameters");
		car.setName(request.getParameter("name"));
		car.setDesc(request.getParameter("description"));
		car.setUrlPhoto(request.getParameter("url_photo"));
		car.setUrlVideo(request.getParameter("url_video"));
		car.setLatitude(request.getParameter("latitude"));
		car.setLongitude(request.getParameter("longitude"));
		car.setType(request.getParameter("type"));
		log("Returning to the doPost method");
		return car;
	}

	public void initConnection() throws ServletException {
		// url values are mysql or cloudsql
		// log("Inside initConnection. Connection will be opened");
		this.url = System.getProperty("mysql");

		log("connecting to: " + url);
		try {
			BaseDao baseDao = new BaseDao("mysql", url);
			conn = BaseDao.conn;
			log("connected to the Cloud SQL instance");
		} catch (SQLException e) {
			System.err.println(url);
			log("Exception occurred in initConnection");
			throw new ServletException("Unable to connect to Cloud SQL", e);
		}
	}
}
