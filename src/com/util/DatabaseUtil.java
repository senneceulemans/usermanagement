package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtil {
	
	private static Properties dbProperties = null;
	
	public static void loadProperties() {
      if(dbProperties == null) {
    	  	dbProperties = new Properties();
    	  	InputStream input = null;
			try {
				input = new FileInputStream("config.properties");
				dbProperties.load(input);								
			} catch (IOException io) {
				io.printStackTrace();
			}
		}      
     }
     	
    public static Connection getConnection() {				
		try {
			loadProperties();
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + dbProperties.getProperty("url") +"/"+ dbProperties.getProperty("dbname"), dbProperties.getProperty("username"), dbProperties.getProperty("password"));
			return connection;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}   	       
    }
}


