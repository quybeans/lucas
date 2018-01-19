package com.lucas;

import com.jaunt.UserAgent;
import com.lucas.db.DAOManager;
import com.lucas.scrapper.yell.YellScrapper;
import com.lucas.utils.DbConnector;

import java.util.Scanner;

public class LucasScrapper {

  public static void main(String[] args) {

    String username = "beaniekun";
    String password = "Bikini123";
    String url = "jdbc:sqlserver://psn.cuzla8tgewqx.ap-southeast-1.rds.amazonaws.com:3012;databaseName=psn";

    DbConnector connector = new DbConnector(url, username, password);
    DAOManager daoManager = new DAOManager(connector);
    UserAgent userAgent = new UserAgent();
    Scanner s = new Scanner(System.in);

    System.out.println("Welcome to Lucas scrapper, this is a part of PSN project.");
    System.out.println("-----------------------------------------------");
    System.out.println("First, Select the yellow page you want to crawl");
    System.out.println("1. Yell.com");
    System.out.println("-----------------------------------------------");

    int pageSelect = s.nextInt();

      switch (pageSelect) {
        case 1:
          System.out.println("Yell scrapper selected.");
          System.out.println("Type the location: ");
          String location = s.next();

          System.out.println("Type the keyword: ");
          String keyword = s.next();

          System.out.println("Type the category key: ");
          String cateCode = s.next();

          YellScrapper yell = new YellScrapper(userAgent, daoManager, 5);
          yell.run(keyword, location, "GBR", cateCode);

          break;
      }
  }
}
