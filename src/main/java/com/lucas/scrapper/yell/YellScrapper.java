package com.lucas.scrapper.yell;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import com.lucas.db.CompanyDAO;
import com.lucas.db.DAOManager;
import com.lucas.model.Address;
import com.lucas.model.Company;
import com.lucas.utils.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class YellScrapper {

  private UserAgent userAgent;
  private int sleeptime;
  private DAOManager daoManager;

  public YellScrapper(UserAgent userAgent, DAOManager daoManager) {
    this.userAgent = userAgent;
    this.daoManager = daoManager;
    this.sleeptime = 0;
  }

  public YellScrapper(UserAgent userAgent, DAOManager daoManager, int sleeptime) {
    this.userAgent = userAgent;
    this.daoManager = daoManager;
    this.sleeptime = sleeptime;
  }

  public void run(String keyword, String locationKeyword, String countryCode, String categoryCode) {
    try {
      for (int i = 1; i <= 10; i++) {
        List<Company> companies = this.crawl(keyword, locationKeyword, countryCode, categoryCode, i);
        System.out.println("Crawled a page, writing to database.");
        writeToDb(this.daoManager, companies);

        System.out.println("Crawled a page, sleep for " + this.sleeptime + " seconds.");
        TimeUnit.SECONDS.sleep(sleeptime);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private List<Company> crawl(String keyword, String location, String countryCode, String categoryCode, int pageNo) {
    List<Company> result = new ArrayList<>();
    try {
      this.userAgent.visit(generateUrl(keyword, location, pageNo));
      Elements capsules = userAgent.doc.findEach(CONSTANTS.ARTICLE_BUSINESS_CAPSULE);

      capsules.forEach((Element element) -> {
        Company company = extractCompanyInfo(element, countryCode, categoryCode);
        result.add(company);
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

  private Company extractCompanyInfo(Element capsule, String countryCode, String categoryCode) {
    try {
      String name = extractValue(capsule, CONSTANTS.CAPSULE_TIT);
      String addressString = extractValue(capsule, CONSTANTS.STREET_ADDRESS);
      String location = extractValue(capsule, CONSTANTS.ADDRESS_LOCALITY);
      String postalCode = extractValue(capsule, CONSTANTS.POSTAL_CODE);
      String phone = extractValue(capsule, CONSTANTS.SPAN_TEL);
      String website = findWebsite(capsule);
      String thumbnail = extractThumbnail(capsule);

      Address address = new Address(addressString, location, postalCode, countryCode);
      return new Company(name, phone, address, website, thumbnail, countryCode, categoryCode, "", "");
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

  private String extractThumbnail(Element capsule) {
    try {
      String url = capsule.findFirst(CONSTANTS.DIV_LEFT_SIDE).findAttributeValues(CONSTANTS.IMG_SRC).get(0);
      if (!url.equals(CONSTANTS.DUMMY_IMG_SRC)) {
        return url;
      } else {
        return "";
      }
    } catch (JauntException | IndexOutOfBoundsException e) {
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

  private void writeToDb(DAOManager daoManager, List<Company> companies) {
    try {
      CompanyDAO companyDao = daoManager.getCompanyDAO(); // Open connection to db
      companies.forEach((Company company) -> {
          String id = UUID.randomUUID().toString();
          daoManager.execute((DAOManager manager) -> companyDao.create(id, company));
        }
      );
    } finally {
      daoManager.closeConnection();
    }
  }
}
