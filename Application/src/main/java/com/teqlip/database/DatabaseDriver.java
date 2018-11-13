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
      //connection=DriverManager.getConnection("jdbc:mysql://mathlab.utsc.utoronto.ca/cscc43f18_leunga56?useUnicode=true&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC", "leunga56", "leunga56");
    } catch (Exception e) {
      System.out.println("Something went wrong with connection...");
      e.printStackTrace();
    }
    
    return connection;
  }

}
