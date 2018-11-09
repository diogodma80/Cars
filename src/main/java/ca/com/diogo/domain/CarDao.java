package ca.com.diogo.domain;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unchecked")
public class CarDao extends HibernateDao<Car> {

	public CarDao() {
		super(Car.class);
	}

	public Car getCarById(Long id) {
		return super.get(id);
	}

	public List<Car> findByName(String name) {
		Query q = getSession().createQuery("from Car where lower(name) like lower(?)");
		q.setString(0,  "%" + name + "%");
		return q.list();
	}

	public List<Car> findByType(String type){
		Query q = getSession().createQuery("from Car where type=?");
		q.setString(0,  type);
		List<Car> cars = q.list();
		return cars;
	}

	public List<Car> getCars(){
		Query q = getSession().createQuery("from Car");
		List<Car> cars = q.list();
		return cars;
	}

	//inserts or updates the car
	public void save(Car c) {
		super.save(c);
	}

	//deletes the car by the id
	public boolean delete(Long id) {
		Car c = get(id);
		delete(c);
		return true;
	}
}

