package com.lucas.yell;

import com.jaunt.*;
import com.lucas.model.Address;
import com.lucas.model.Company;
import com.lucas.utils.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class YellScrapper {

  private UserAgent userAgent;
  private List<Company> result;

  public YellScrapper(UserAgent userAgent) {
    this.userAgent = userAgent;
    this.result = new ArrayList<>();
  }

  public List<Company> getResult() {
    return this.result;
  }

  public void run(String keyword, String location) {
    try {
      for (int i = 1; i <= 1; i++) {
        this.crawl(keyword, location, i);
        System.out.println("Crawled 1 page.");
        TimeUnit.SECONDS.sleep(5);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private List<Company> crawl(String keyword, String location, int pageNo) {
    try {
      this.userAgent.visit(generateUrl(keyword, location, pageNo));
      Elements capsules = userAgent.doc.findEach(CONSTANTS.ARTICLE_BUSINESS_CAPSULE);

      capsules.forEach((Element element) -> {
        Company company = extractCompanyInfo(element);
        this.result.add(company);
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

  private Company extractCompanyInfo(Element capsule) {
    try {
      String name = extractValue(capsule, CONSTANTS.CAPSULE_TIT);
      String addressString = extractValue(capsule, CONSTANTS.STREET_ADDRESS);
      String location = extractValue(capsule, CONSTANTS.ADDRESS_LOCALITY);
      String postalCode = extractValue(capsule, CONSTANTS.POSTAL_CODE);
      String phone = extractValue(capsule, CONSTANTS.SPAN_TEL);
      String website = findWebsite(capsule);

      Address address = new Address(addressString, location, postalCode);
      return new Company(name, phone, address, website, "");
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private String extractValue(Element capsule, String query) {
    try {
      return TextUtils.cleanText(capsule.findFirst(query).innerText());
    } catch (JauntException e) {
      return "";
    }
  }

  private String findWebsite(Element capsule) {
    try {
      Element websiteDiv = capsule.findFirst(CONSTANTS.WEBSITE_DIV);
      Element websiteATag = websiteDiv.getParent().getParent();
      return websiteATag.findAttributeValues(CONSTANTS.ATT_HREF_A).get(0);
    } catch (JauntException | IndexOutOfBoundsException e) {
      return "";
    }
  }

  private String generateUrl(String keyword, String location, int pageNo) {
    try {
      String keywordEncoded = URLEncoder.encode(keyword, CONSTANTS.ENCODE_METHOD);
      String locationEncoded = URLEncoder.encode(location, CONSTANTS.ENCODE_METHOD);
      return CONSTANTS.BASE_URL + "?keywords=" + keywordEncoded + "&location=" + locationEncoded + "&pageNum=" + pageNo;
    } catch (UnsupportedEncodingException e) {
      e.fillInStackTrace();
      return null;
    }
  }
}
