package com.lucas.db;


import com.lucas.model.Address;
import com.lucas.model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyDAO extends GenericDAO<Company> {

  private final static String TABLENAME = "company";

  public CompanyDAO(Connection con) {
    super(con, TABLENAME);
  }

  @Override
  public ArrayList<Company> getAll() {
    ArrayList<Company> result = new ArrayList<>();
    String query = "SELECT * FROM " + this.tableName;
    PreparedStatement counter;

    try {
      counter = this.conn.prepareStatement(query);
      ResultSet rs = counter.executeQuery();

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String phone = rs.getString("phone");
        String streetAddress = rs.getString("address");
        String location = rs.getString("location");
        String postalCode = rs.getString("postal");
        String website = rs.getString("website");
        String profileImgUrl = rs.getString("avatar");

        Address address = new Address(streetAddress, location, postalCode);
        result.add(new Company(name, phone, address, website, profileImgUrl));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  @Override
  public int create(Company company) {
    String query = "INSERT INTO "
        + this.tableName
        + " (name, phone, address, location, postal, website, avatar)"
        + " VALUES (?, ?, ?, ?, ?, ?, ?)";

    PreparedStatement stmt = null;
    try {
      stmt = conn.prepareStatement(query);
      stmt.setString(1, company.getName());
      stmt.setString(2, company.getPhone());
      stmt.setString(3, company.getAddress().getStreetAddress());
      stmt.setString(4, company.getAddress().getLocation());
      stmt.setString(5, company.getAddress().getPostalCode());
      stmt.setString(6, company.getWebsite());
      stmt.setString(7, company.getProfilePicUrl());

      return stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }
}

