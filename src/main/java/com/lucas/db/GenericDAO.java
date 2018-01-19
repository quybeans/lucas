package com.lucas.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class GenericDAO<T> {

  public abstract ArrayList<T> getAll() throws SQLException;
  public abstract int create(String id, T item) throws SQLException;

  final String tableName;
  Connection conn;

  GenericDAO(Connection conn, String tableName) {
    this.tableName = tableName;
    this.conn = conn;
  }
}
