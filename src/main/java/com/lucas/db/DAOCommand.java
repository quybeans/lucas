package com.lucas.db;

public interface DAOCommand {
  public Object execute(DAOManager daoManager);
}
