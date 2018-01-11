package com.lucas.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {

  public Connection connect() {

    String userName = "sa";
    String password = "Bikini123!";
    String url = "jdbc:sqlserver://localhost:32772;databaseName=psn";

    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      Connection conn = DriverManager.getConnection(url, userName, password);
      return conn;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
