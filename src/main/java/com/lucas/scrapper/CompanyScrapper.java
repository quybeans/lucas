package com.lucas.scrapper;

import com.jaunt.UserAgent;
import com.lucas.model.Address;
import com.lucas.model.Company;

public abstract class CompanyScrapper {

  abstract String extractName();
  abstract String extractPhone();
  abstract Address extractAddress();
  abstract String extractWebsite();
  abstract String extractProfilePicUrl();

  private final UserAgent agent;
  private final int sleep;

  public CompanyScrapper(UserAgent agent) {
    this.agent = agent;
    this.sleep = 0;
  }

  public CompanyScrapper(UserAgent agent, int sleep) {
    this.agent = agent;
    this.sleep = sleep;
  }

  public void crawl(int i) {
    extractCompany();
  }

  private Company extractCompany() {
    String name = extractName();
    String phone = extractPhone();
    Address address = extractAddress();
    String website = extractWebsite();
    String profilePicUrl = extractProfilePicUrl();

    return new Company(name, phone, address, website, profilePicUrl);
  }
}
