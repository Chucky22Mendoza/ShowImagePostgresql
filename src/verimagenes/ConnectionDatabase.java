/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verimagenes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author login
 */
public class ConnectionDatabase {
    private Connection connection;
    private final String urlDatabase = "jdbc:postgresql://localhost:5432/yimibus";
    private final String user = "postgres";
    private final String password = "12345";
    
    /**
     * Connection for database
     * 
     * @return 
     */
    public Connection getConnection() {
	return connection;
    }

    /**
     * Init connection
     * 
     * @param connection 
     */
    public void setConnection(Connection connection) {
	this.connection = connection;
    }
    
    /**
     * New connection
     */
    public void createConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(urlDatabase, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
	} finally {
            System.out.println("Conectado");
        }
    }
    
    /**
     * Close connection
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
