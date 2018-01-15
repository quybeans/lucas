package com.lucas.model;

public class TrangVangCategory {
  private int id;
  private String name;
  private String categoryCode;
  private String link ;
  public TrangVangCategory(){

  }
  public TrangVangCategory(int id,String name, String categoryCode, String link) {
    this.id = id;
    this.name = name;
    this.categoryCode = categoryCode;
    this.link = link;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategoryCode() {
    return categoryCode;
  }

  public void setCategoryCode(String categoryCode) {
    this.categoryCode = categoryCode;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }
}
