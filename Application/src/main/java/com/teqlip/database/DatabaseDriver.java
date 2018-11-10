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
  protected static Connection connectDataBase() {
    Connection connection = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection=DriverManager.getConnection("jdbc:mysql://mathlab.utsc.utoronto.ca/cscc43f18_leunga56?useUnicode=true&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC", "leunga56", "leunga56");
    } catch (Exception e) {
      System.out.println("Something went wrong with connection...");
      e.printStackTrace();
    }
    
    return connection;
  }
  
//  /**
//   * This will initialize the database, or throw a ConnectionFailedException.
//   * @param connection the database you'd like to write the tables to.
//   * @return the connection you passed in, to allow you to continue.
//   * @throws ConnectionFailedException If the tables couldn't be initialized, throw
//   */
//  protected static Connection initialize(Connection connection) throws ConnectionFailedException {
//    if (!initializeDatabase(connection)) {
//      throw new ConnectionFailedException();
//    }
//    return connection;
//  }
//  
//  // maybe need to initialize db in later stage
//  private static boolean initializeDatabase(Connection connection) {
//    Statement statement = null;
//    
//    try {
//      return true;
//      
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return false;
//  }
}
