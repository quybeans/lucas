package com.lucas.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

  private String username;
  private String password;
  private String url;
  private Connection conn;

  public DbConnector(String url, String username, String password) {
    this.username = username;
    this.password = password;
    this.url = url;
    this.conn = null;
  }

  public Connection connect() {
    System.out.println("Connecting");
    try {
      System.out.println("Connecting database ...");
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      this.conn = DriverManager.getConnection(url, username, password);
      System.out.println("Connection open ...");
      return this.conn;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void close() {
    try {
      this.conn.close();
      System.out.println("Connection closed.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
