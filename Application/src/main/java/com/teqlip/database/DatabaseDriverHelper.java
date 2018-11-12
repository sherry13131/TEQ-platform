package com.teqlip.database;

import com.teqlip.database.DatabaseDriver;

import java.sql.Connection;


public class DatabaseDriverHelper extends DatabaseDriver {

  protected static Connection connectOrCreateDataBase() {
    return DatabaseDriver.connectDataBase();
  }

}