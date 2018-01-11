package com.lucas;

import com.jaunt.UserAgent;
import com.lucas.db.DAOManager;
import com.lucas.model.Company;
import com.lucas.utils.DbConnector;
import com.lucas.yell.YellScrapper;

import java.sql.Connection;

public class LucasScrapper {

  public static void main(String[] args) {

    UserAgent userAgent = new UserAgent();
    YellScrapper yell = new YellScrapper(userAgent);

    yell.run("Pizza", "London");
    yell.getResult().forEach((Company company) -> {
      Connection connection = new DbConnector().connect();
      DAOManager daoManager = new DAOManager(connection);
      daoManager.executeAndClose((DAOManager manager) -> daoManager.getCompanyDAO().create(company));
    });
  }
}
