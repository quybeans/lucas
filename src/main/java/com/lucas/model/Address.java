package com.lucas.model;

public class Address {
  private String streetAddress;
  private String location;
  private String postalCode;

  public Address(String streetAddress, String location, String postalCode) {
    this.postalCode = postalCode;
    this.streetAddress = streetAddress;
    this.location = location;
  }

  public String getStreetAddress() {
    return streetAddress;
  }

  public String getLocation() {
    return location;
  }

  public String getPostalCode() {
    return postalCode;
  }
}
