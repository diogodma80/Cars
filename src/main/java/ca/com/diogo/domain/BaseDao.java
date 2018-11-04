package ca.com.diogo.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {
	
	
	public static Connection conn = null;
	
	//url to get local MySQL connection
	static private String localUrl = "jdbc:mysql://localhost/book";
	static private String user = "book";
	static private String password = "book123";

	public BaseDao(){
	}
	
	public BaseDao(String source, String url) throws SQLException {
		if(source == "cloudsql"){
			conn = DriverManager.getConnection(url);
		} else if (source == "mysql"){
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("mysql connection created");
		}
	}
	
	public Connection getConnection() throws SQLException{
		return conn;
	}
	
	public static void main(String[] args){
		BaseDao db = null;
		try {
			db = new BaseDao("mysql", localUrl);
			if(conn != null){
				System.out.println("Connected to MySQL");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
