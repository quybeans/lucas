package com.lucas.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {

  private String username;
  private String password;
  private String url;

  public DbConnector(String url, String username, String password) {
    this.username = username;
    this.password = password;
    this.url =url;
  }

  public Connection connect() {
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      Connection conn = DriverManager.getConnection(url, username, password);
      return conn;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
