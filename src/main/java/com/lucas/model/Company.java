package com.lucas.model;

public class Company {

  private String name;
  private String phone;
  private Address address;
  private String website;
  private String profilePicUrl;
  private String countryCode;
  private String categoryCode;
  private String email;
  private String description;

  public Company(String name,
                 String phone,
                 Address address,
                 String website,
                 String profilePicUrl,
                 String countryCode,
                 String categoryCode,
                 String email,
                 String description) {
    this.name = name;
    this.phone = phone;
    this.address = address;
    this.website = website;
    this.profilePicUrl = profilePicUrl;
    this.countryCode = countryCode;
    this.categoryCode = categoryCode;
    this.email = email;
    this.description = description;
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

  public String getCountryCode() {
    return countryCode;
  }

  public String getCategoryCode() {
    return categoryCode;
  }
}
