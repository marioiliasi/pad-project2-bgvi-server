package com.pad.bgvi.server.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			Class.forName(BGVI_Utils.DB_DRIVER);  
			con = DriverManager.getConnection(BGVI_Utils.DB_URL, BGVI_Utils.DB_USERNAME, BGVI_Utils.DB_PASSWORD);
		} catch (SQLException ex) {
			System.out.println("Failed to create the database connection.");
		}
		return con;
	}
}
