package edu.siying.util;

/**
 * @author siying
 * Connect to MySQL database ecuadordata
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	//static reference to itself
	private static DBConnector instance = new DBConnector();
	
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/ecuadordata";
	public static final String USER = "root";
	public static final String PASSWORD = "root";

	//private constructor
	public DBConnector() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("ERROR: Unable to connect to Database!");
		}
		return connection;
	}
	
	public static Connection getConnection() {
		return instance.createConnection();
	}
	

}
