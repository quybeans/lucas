package com.lucas.yell;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import com.lucas.model.Address;
import com.lucas.model.Company;
import com.lucas.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class YellScrapper {

  private String generateUrl(String keyword, String location) {
    return CONSTANTS.BASE_URL + "?keywords=" + keyword + "&location=" + location + "n&scrambleSeed=833794509";
  }

  public List<Company> run(String keyword, String location) {

    List<Company> result = new ArrayList<>();

    try {
      UserAgent userAgent = new UserAgent();
      userAgent.visit(generateUrl(keyword, location));
      Elements capsules = userAgent.doc.findEach(CONSTANTS.ARTICLE_BUSINESS_CAPSULE);

      capsules.forEach((Element element) -> {
        Company company = extractCompanyInfo(element);
        result.add(company);
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
      e.printStackTrace();
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
}
