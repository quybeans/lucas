package com.lucas.db;


import com.lucas.model.Address;
import com.lucas.model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyDAO extends GenericDAO<Company> {

  private final static String TABLENAME = "Company";

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
        String nationCode = rs.getString("nationCode");
        String categoryCode = rs.getString("categoryCode");
        String email = rs.getString("email");
        String description  = rs.getString("description");

        Address address = new Address(streetAddress, location, postalCode, nationCode);
        result.add(new Company(
            name, phone, address, website,
            profileImgUrl, nationCode, categoryCode,
            email, description
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  @Override
  public int create(String id, Company company) {
    String query = "INSERT INTO "
        + this.tableName
        + " (id, name, ownerId, phone, address, city,"
        + " nationCode, website, thumbnail, categoryCode, status)"
        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    PreparedStatement stmt;
    try {
      stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      stmt.setString(2, company.getName());
      stmt.setString(3, "admin");
      stmt.setString(4, company.getPhone());
      stmt.setString(5, company.getAddress().getStreetAddress());
      stmt.setString(6, company.getAddress().getCity());
      stmt.setString(7, company.getAddress().getNationCode());
      stmt.setString(8, company.getWebsite());
      stmt.setString(9, company.getProfilePicUrl());
      stmt.setString(10, company.getCategoryCode());
      stmt.setString(11, "OPEN");

      return stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }
}

