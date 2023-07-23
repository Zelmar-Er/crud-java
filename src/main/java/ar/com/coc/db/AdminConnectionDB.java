package ar.com.coc.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdminConnectionDB {
	// creamos un metodo estatico, no creamos un objeto para usar este metodo
	public static Connection getConnection() {
		String host = "localhost"; // 127.0.0.1
		String port = "3306";
		String password = "";
		String username = "root";
		String nombreDB = "desafio";
		
		// driver para conectar con la DB
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		// aplicamos manage de excepciones que se ve en el curso avanzado
		Connection connection;
		try {
			// com.mysql.cj.jdbc; Construye una clase a partir de un objeto compilado
			Class.forName(driverClassName);
			// url de conexion
			String url = "jdbc:mysql://"+host+":"+port+"/"+nombreDB+"?serverTimeZone=UTC&useSSL=false";
			connection = DriverManager.getConnection(url,username,password);
		}
		catch(Exception e) {
			connection = null;
		}
		return connection;
	}
}
