package com.teqlip.database;

import com.teqlip.exceptions.ConnectionFailedException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseDriver {
  /**
   * Connect to existing database.
   * @return the database connection.
   */
  public static Connection connectDataBase() {
    Connection connection = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/assignmentdb", "root", "");
    } catch (Exception e) {
      System.out.println("Something went wrong with connection...");
      e.printStackTrace();
    }
    
    return connection;
  }

}
