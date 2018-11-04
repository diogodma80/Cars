package ca.com.diogo.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarService {

	// private final static Logger LOGGER =
	// Logger.getLogger(CarService.class.getName());

	private CarDao dao = new CarDao();

	public CarService() {
		super();
	}

	public Car getCar(Long id) {
		// LOGGER.log(Level.INFO, "CarService.getCar(Long id)");
		try {
			// LOGGER.log(Level.INFO, "calling dao.getCarById(id) from getCar()");
			return dao.getCarById(id);
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Car> findByName(String name) {
		// LOGGER.log(Level.INFO, "CarService.findByName(String name)");
		try {
			// LOGGER.log(Level.INFO, "calling dao.findByName(name) from findByName(String
			// name)");
			return dao.findByName(name);
		} catch (Exception e) {
			return null;
		}
	}

	public List<Car> findByType(String type) {
		// LOGGER.log(Level.INFO, "CarService.findByType(String type)");
		try {
			// LOGGER.log(Level.INFO, "calling dao.findByType(type) from findByType(String
			// type)");
			return dao.findByType(type);
		} catch (Exception e) {
			return null;
		}
	}

	public List<Car> getCars() {
		// LOGGER.log(Level.INFO, "CarService.getCars()");
		try {
			// LOGGER.log(Level.INFO, "calling dao.getCars() from getCars()");
			List<Car> cars = dao.getCars();
			return cars;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Car>();
		}
	}

	public boolean save(Car c) {
		// LOGGER.log(Level.INFO, "CarService.save(Car c)");
		try {
			// LOGGER.log(Level.INFO, "calling dao.save(c) from save(Car c)");
			dao.save(c);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean delete(Long id) {
		// LOGGER.log(Level.INFO, "CarService.delete(Long id)");
		try {
			// LOGGER.log(Level.INFO, "calling dao.delete(id); from delete(Long id)");
			return dao.delete(id);
		} catch (SQLException e) {
			return false;
		}
	}
}
