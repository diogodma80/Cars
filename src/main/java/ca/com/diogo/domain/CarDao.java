package ca.com.diogo.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CarDao extends BaseDao {

	// private Connection conn;
	public CarDao() {
		super();
	}

	public static Long getGeneratedId(Statement stmt) throws SQLException {
		ResultSet rs = null;
		rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			Long id = rs.getLong(1);
			return id;
		}
		return 0L;
	}

	public Car getCarById(Long id) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = BaseDao.conn;
			stmt = conn.prepareStatement("SELECT * FROM car WHERE id = ?");
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Car c = createCar(rs);
				return c;
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
//			if (conn != null) {
//				conn.close();
//			}
		}

		return null;
	}

	public List<Car> findByName(String name) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Car> cars = new ArrayList<Car>();

		try {
			conn = BaseDao.conn;
			stmt = conn.prepareStatement("SELECT * FROM car WHERE LOWER(name) LIKE ?");
			stmt.setString(1, "%" + name.toLowerCase() + "%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Car car = createCar(rs);
				cars.add(car);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
//			if (conn != null) {
//				conn.close();
//			}
		}
		return cars;
	}

	public List<Car> findByType(String type) throws SQLException {
		List<Car> cars = new ArrayList<Car>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = BaseDao.conn;
			stmt = conn.prepareStatement("SELECT * FROM car WHERE type = ?");
			stmt.setString(1, type);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Car car = createCar(rs);
				cars.add(car);
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
//			if (conn != null) {
//				conn.close();
//			}
		}
		return cars;
	}

	public List<Car> getCars() throws SQLException {

		List<Car> cars = new ArrayList<Car>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// conn = getConnection();
			conn = BaseDao.conn;
			stmt = conn.prepareStatement("SELECT * FROM car");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Car car = createCar(rs);
				cars.add(car);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
//			if (conn != null) {
//				conn.close();
//			}
		}

		return cars;
	}

	public Car createCar(ResultSet rs) throws SQLException {
		Car car = new Car();
		car.setId(rs.getLong("id"));
		car.setName(rs.getString("name"));
		car.setDesc(rs.getString("description"));
		car.setLatitude(rs.getString("latitude"));
		car.setLongitude(rs.getString("longitude"));
		car.setUrlPhoto(rs.getString("url_Photo"));
		car.setUrlVideo(rs.getString("url_Video"));
		car.setType(rs.getString("type"));

		return car;
	}

	public void save(Car c) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		System.out.println("Into CarDao.save");
		try {
			conn = BaseDao.conn;
			System.out.println(conn.toString());
			if (c.getId() == null) {
				System.out.println("Id == null - setting the insert query");
				stmt = conn.prepareStatement(
						"INSERT INTO car(name, description, url_photo, url_video, latitude, longitude, type) VALUES(?, ?, ?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement(
						"UPDATE car SET name=?, description=?, url_photo=?, url_video=?, latitude=?, longitude=?, type=? WHERE id=?");
			}
			stmt.setString(1, c.getName());
			stmt.setString(2, c.getDesc());
			stmt.setString(3, c.getUrlPhoto());
			stmt.setString(4, c.getUrlVideo());
			stmt.setString(5, c.getLatitude());
			stmt.setString(6, c.getLongitude());
			stmt.setString(7, c.getType());
			// Update
			if (c.getId() != null) {
				stmt.setLong(8, c.getId());
			}
			System.out.println("Execute update and set count value");
			int count = stmt.executeUpdate();
			System.out.println("Executed update");
			if (count == 0) {
				throw new SQLException("Exception when saving the car");
			}
			// if saving worked, read he auto increment
			if (c.getId() == null) {
				Long id = getGeneratedId(stmt);
				System.out.println("generated Id: " + id);
				c.setId(id);
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
//			if (conn != null) {
//				conn.close();
//			}
		}
	}

	public boolean delete(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = BaseDao.conn;
			stmt = conn.prepareStatement("DELETE FROM car WHERE id=?");
			stmt.setLong(1, id);
			int count = stmt.executeUpdate();
			boolean ok = count > 0;
			return ok;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
//			if (conn != null) {
//				conn.close();
//			}
		}
	}
}

