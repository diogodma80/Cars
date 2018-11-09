package ca.com.diogo.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CarService {

	// private final static Logger LOGGER =
	// Logger.getLogger(CarService.class.getName());

	@Autowired
	private CarDao dao;

	public CarService() {
		super();
	}

	public Car getCar(Long id) {
		return dao.getCarById(id);
	}

	public List<Car> findByName(String name) {
		return dao.findByName(name);
	}

	public List<Car> findByType(String type) {
		return dao.findByType(type);
	}

	public List<Car> getCars() {
		List<Car> cars = dao.getCars();
		return cars;
	}

	@Transactional(rollbackFor=Exception.class)
	public boolean save(Car c) {
		dao.saveOrUpdate(c);
		return true;
	}

	@Transactional(rollbackFor=Exception.class)
	public boolean delete(Long id) {
		return dao.delete(id);
	}
}
