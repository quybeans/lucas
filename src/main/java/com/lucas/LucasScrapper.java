package com.lucas;

import com.jaunt.UserAgent;
import com.lucas.db.DAOManager;
import com.lucas.model.Company;
import com.lucas.utils.DbConnector;
import com.lucas.yell.YellScrapper;

import java.sql.Connection;

public class LucasScrapper {


  public static void main(String[] args) {

    String username = "sa";
    String password = "Bikini123!";
    String url = "jdbc:sqlserver://localhost:32772;databaseName=psn";

    UserAgent userAgent = new UserAgent();
    YellScrapper yell = new YellScrapper(userAgent, 5);

    yell.run("Pizza", "London");
    yell.getResult().forEach((Company company) -> {
      Connection connection = new DbConnector(url, username, password).connect();
      DAOManager daoManager = new DAOManager(connection);
      daoManager.executeAndClose((DAOManager manager) -> daoManager.getCompanyDAO().create(company));
    });
  }
}
