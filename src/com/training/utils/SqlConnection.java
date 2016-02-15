package com.training.utils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SqlConnection {

	public static Connection getOracleConnection(){
		Connection con = null;
		try {
			Properties prop = new Properties();
			FileInputStream inStream = new FileInputStream(new File("DbConnection.properties"));
			prop.load(inStream);
			
			Class.forName(prop.getProperty("db.className"));
			
			con = DriverManager.getConnection(prop.getProperty("db.url"),
											prop.getProperty("db.userName"),
											prop.getProperty("db.passWord")
											);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}
}
