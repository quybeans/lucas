package com.lucas.yell;

public class CONSTANTS {
  protected static final String BASE_URL = "https://www.yell.com/ucs/UcsSearchAction.do";
  protected static final String ARTICLE_BUSINESS_CAPSULE = "<article class=.*\\bbusinessCapsule\\b.*>";
  protected static final String CAPSULE_TIT = "<div class=.*\\bbusinessCapsule--titSpons\\b.*>";
  protected static final String STREET_ADDRESS = "<span itemprop=.*\\bstreetAddress\\b.*>";
  protected static final String POSTAL_CODE = "<span itemprop=.*\\bpostalCode\\b.*>";
  protected static final String ADDRESS_LOCALITY = "<span itemprop=.*\\baddressLocality\\b.*>";
  protected static final String SPAN_TEL = "<span class=.*\\bbusiness--telephoneNumber\\b.*>";
  protected static final String SPAN_CTA = "<a class=.*\\bbusinessCapsule--ctaItem\\b.*>";

  protected static final String WEBSITE_DIV = "<div class=.*\\bicon-Business-website\\b.*>";
  protected static final String ATT_HREF_A = "<a href>";

  protected static final String ENCODE_METHOD = "UTF-8";
}
