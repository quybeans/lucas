package com.lucas.utils;

import java.util.regex.Pattern;

public class TextUtils {

  public static String cleanText(String raw) {
    return new TextUtils().clean(raw);
  }

  private String clean(String raw) {
    return rtrim(ltrim(raw));
  }

  private String ltrim(String raw) {
    return Pattern.compile("^\\s+").matcher(raw).replaceAll("");
  }

  private String rtrim(String raw) {
    return Pattern.compile("\\s+$").matcher(raw).replaceAll("");
  }
}
