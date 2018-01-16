package com.lucas.db;

import com.lucas.model.TrangVangCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrangVangCategoryDAO extends GenericDAO<TrangVangCategory>  {

  private final static String TABLENAME = "TrangVangCategory";

  public TrangVangCategoryDAO(Connection con) {
    super(con, TABLENAME);
  }

  @Override
  public ArrayList<TrangVangCategory> getAll() {
    ArrayList<TrangVangCategory> result = new ArrayList<>();
    String query = "SELECT * FROM " + this.tableName;
    PreparedStatement counter;

    try {
      counter = this.conn.prepareStatement(query);
      ResultSet rs = counter.executeQuery();

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String link = rs.getString("link");

        result.add(new TrangVangCategory(id,name,"",link)
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  @Override
  public int create(TrangVangCategory tvc) {
    String query = "INSERT INTO "
        + this.tableName
        + " (Name,Link,CategoryCode)"
        + " VALUES (?, ?, ?)";

    PreparedStatement stmt = null;
    try {
      stmt = conn.prepareStatement(query);
      stmt.setString(1, tvc.getName());
      stmt.setString(2, tvc.getLink());
      stmt.setString(3, "");

      return stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }
}
