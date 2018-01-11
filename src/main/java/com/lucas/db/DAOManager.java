package com.lucas.db;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOManager {

  private Connection connection = null;
  private CompanyDAO companyDAO = null;

  public DAOManager(Connection connection) {
    this.connection = connection;
  }

  public CompanyDAO getCompanyDAO() {
    if (this.companyDAO == null) {
      this.companyDAO = new CompanyDAO(this.connection);
    }

    return companyDAO;
  }

  public Object executeAndClose(DAOCommand command) {
    try{
      return command.execute(this);
    } finally {
      try {
        this.connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Connection might be closed.");
      }
    }
  }
}
