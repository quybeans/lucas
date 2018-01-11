package com.lucas.model;

public class Company {

  private String name;
  private String phone;
  private Address address;
  private String website;
  private String profilePicUrl;

  public Company(String name,
                 String phone,
                 Address address,
                 String email,
                 String profilePicUrl) {
    this.name = name;
    this.phone = phone;
    this.address = address;
    this.website = email;
    this.profilePicUrl = profilePicUrl;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public Address getAddress() {
    return address;
  }

  public String getWebsite() {
    return website;
  }

  public String getProfilePicUrl() {
    return profilePicUrl;
  }
}
