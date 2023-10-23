package com.tavsanci.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Testjdbc {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
		String user = "hbstudent";
		String password = "hbstudent";
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl,user,password);
			System.out.println(connection);
		} catch (Exception e) {
			
		}
	}

}
