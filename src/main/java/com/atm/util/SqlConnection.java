package com.atm.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnection {
	private static final String URL = "jdbc:mariadb://localhost:3306/atmdb";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "admin1";
	
	private static final SqlConnection INSTANCE = new SqlConnection();
	private static Connection con;
	
	private SqlConnection() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlConnection getInstance() {
		return INSTANCE;
	}
	
	public static Connection getConnection() {
		return con;
	}
}