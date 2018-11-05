package ca.com.diogo.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import ca.com.diogo.domain.BaseDao;
import ca.com.diogo.domain.CarService;

public class InitConnection {
	Connection conn = null;
	String url = null;

	public InitConnection() throws ServletException {
		// url values are mysql or cloudsql
		this.url = System.getProperty("mysql");

		try {
			BaseDao baseDao = new BaseDao("mysql", url);
			conn = BaseDao.conn;
		} catch (SQLException e) {
			System.err.println(url);
			throw new ServletException("Unable to connect to Cloud SQL", e);
		}
	}
}