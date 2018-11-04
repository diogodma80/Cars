package ca.com.diogo.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import ca.com.diogo.domain.BaseDao;
import ca.com.diogo.domain.Car;
import ca.com.diogo.domain.CarService;
import junit.framework.TestCase;

public class CarTest extends TestCase {

	private CarService carService = new CarService();

	private void getBaseDaoConnection() {
		try {
			BaseDao basedao = new BaseDao("mysql", "jdbc:mysql://localhost/book");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testListCars() {

		getBaseDaoConnection();
		List<Car> cars = carService.getCars();
		// assertNotNull(cars);

		// valida se encontrou algo
		// assertTrue(cars.size() > 0);

		// Car car = carService.getCar(1L);
		// assertNotNull(car);

		// valida se encontrou o Tucker
		Car tucker = carService.findByName("Tucker 1948").get(0);
		assertEquals("Tucker 1948", tucker.getName());

		// valida se encontrou Ferrari
		// Car ferrari = carService.findByName("Ferrari FF").get(0);
		// assertEquals("Ferrari FF", ferrari.getName());

		// valida se encontrou Bugatti
		// Car bugatti = carService.findByName("Bugatti Veyron").get(0);
		// assertEquals("Bugatti Veyron", bugatti.getName());
	}

	@Test
	public void testSaveDeleteCars() {
		getBaseDaoConnection();
		Car c = new Car();
		c.setName("Test3");
		c.setDesc("Test3 desc");
		c.setUrlPhoto("url photo here");
		c.setUrlVideo("url video here");
		c.setLatitude("lat3");
		c.setLongitude("lon3");
		c.setType("type3");
		carService.save(c);
		// id of the saved car
		Long id = c.getId();
		assertNotNull(id);

		// search to on the db to confirm if the car was saved
		c = carService.getCar(id);
		assertEquals("Test3", c.getName());
		assertEquals("Test3 desc", c.getDesc());
		assertEquals("url photo here", c.getUrlPhoto());
		assertEquals("url video here", c.getUrlVideo());
		assertEquals("lat3", c.getLatitude());
		assertEquals("lon3", c.getLongitude());
		assertEquals("type3", c.getType());

		// updates the car
		c.setName("Test UPDATE");
		carService.save(c);

		//search the car again (it will be updated now)
		assertEquals("Test UPDATE", c.getName());

		// delete the car
		carService.delete(id);

		// search the car again
		c = carService.getCar(id);

		// this time the car does not exist anymore
		assertNull(c);
	}
}
