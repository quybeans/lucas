package com.lucas;

import com.lucas.db.DAOCommand;
import com.lucas.db.DAOManager;
import com.lucas.model.Address;
import com.lucas.model.Company;
import com.lucas.utils.DbConnector;

import java.sql.Connection;

public class LucasScrapper {

  public static void main(String[] args) {

    Connection connection = new DbConnector().connect();
    DAOManager daoManager = new DAOManager(connection);

    Company test = new Company(
        "bean",
        "01272727",
        new Address("Phu Tho", "HCMC", "7000"),
        "uniq.vn",
        "google.com"
    );

    daoManager.executeAndClose(new DAOCommand() {
      @Override
      public Object execute(DAOManager daoManager) {
        int x = daoManager.getCompanyDAO().getAll().size();
        System.out.println(x);
        return daoManager.getCompanyDAO().getAll();
      }
    });

//    UserAgent userAgent = new UserAgent();
//    YellScrapper yell = new YellScrapper(userAgent);
//    yell.run("Pizza", "London");
//    System.out.println(yell.getResult().size());
  }
}
