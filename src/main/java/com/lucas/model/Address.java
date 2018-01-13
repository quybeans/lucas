package com.lucas.model;

public class Address {
  private String streetAddress;
  private String city;
  private String postalCode;
  private String nationCode;

  public Address(String streetAddress, String location, String postalCode, String nationCode) {
    this.postalCode = postalCode;
    this.streetAddress = streetAddress;
    this.city = location;
    this.nationCode = nationCode;
  }

  public String getStreetAddress() {
    return streetAddress;
  }

  public String getCity() {
    return city;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getNationCode() {
    return nationCode;
  }
}
