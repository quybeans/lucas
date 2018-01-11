package com.lucas.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class GenericDAO<T> {

  public abstract ArrayList<T> getAll() throws SQLException;
  public abstract int create(T item) throws SQLException;

  protected final String tableName;
  protected Connection conn;

  protected GenericDAO(Connection conn, String tableName) {
    this.tableName = tableName;
    this.conn = conn;
  }
}
