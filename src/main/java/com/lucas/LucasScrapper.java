package com.lucas;

import com.jaunt.UserAgent;
import com.lucas.db.DAOManager;
import com.lucas.scrapper.yell.YellScrapper;
import com.lucas.utils.DbConnector;

public class LucasScrapper {

  public static void main(String[] args) {

    String username = "beaniekun";
    String password = "Bikini123";
    String url = "jdbc:sqlserver://psn.cuzla8tgewqx.ap-southeast-1.rds.amazonaws.com:3012;databaseName=psn";

    DbConnector connector = new DbConnector(url, username, password);
    DAOManager daoManager = new DAOManager(connector);
    UserAgent userAgent = new UserAgent();
    YellScrapper yell = new YellScrapper(userAgent, daoManager, 5);

    yell.run("Fashion", "Nottingham", "UK", "FSH");
  }
}
