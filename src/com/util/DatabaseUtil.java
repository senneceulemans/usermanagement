package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.derby.drda.NetworkServerControl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
     	
    public static Connection getConnection() throws ClassNotFoundException {				

			loadProperties();
			Connection connection = null;
			try {
				// Get connection for MySql		
				 Class.forName("com.mysql.jdbc.Driver");
				 connection = DriverManager.getConnection("jdbc:mysql://" + dbProperties.getProperty("url") +"/"+ dbProperties.getProperty("dbname"), dbProperties.getProperty("username"), dbProperties.getProperty("password"));
	
			
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}            
            return connection;                      			
    }

}


