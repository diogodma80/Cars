package ca.com.diogo.domain;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="cars")
public class ListCars implements Serializable{
	
	//private final static Logger LOGGER = Logger.getLogger(ListCars.class.getName());
	private static final long serialVersionUID = -6266595231547106372L;
	private List<Car> cars;
	
	@XmlElement(name="car")
	public List<Car> getCars() {
		return cars;
	}
	
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	@Override
	public String toString() {
		return "ListCars [cars=" + cars + "]";
	}
}
