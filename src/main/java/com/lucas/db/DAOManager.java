package com.lucas.db;

import com.lucas.utils.DbConnector;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOManager {

  private DbConnector connector;
  private CompanyDAO companyDAO;

  public DAOManager(DbConnector connector) {
    this.connector = connector;
    this.companyDAO = null;
  }

  public CompanyDAO getCompanyDAO() {
    if (this.companyDAO == null) {
      this.companyDAO = new CompanyDAO(connector.connect());
    }

    return this.companyDAO;
  }

  public Object executeAndClose(DAOCommand command) {
    try{
      return execute(command);
    } finally {
      closeConnection();
    }
  }

  public Object execute(DAOCommand command) {
    return command.execute(this);
  }

  public void closeConnection() {
    this.companyDAO = null;
    this.connector.close();
  }
}
