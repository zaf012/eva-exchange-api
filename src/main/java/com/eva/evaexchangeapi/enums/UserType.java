package com.eva.evaexchangeapi.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum UserType {
  ADMIN("ADMIN"),
  TRADER("TRADER");

  private static final Map<String, UserType> lookup = new HashMap<>();

  static {
    for (UserType source : UserType.values()) {
      lookup.put(source.getValue(), source);
    }
  }

  private final String value;

  UserType(String value) {
    this.value = value;
  }

  public static UserType findByValue(String value) {
    return Optional.ofNullable(lookup.get(value))
        .orElseThrow(
            () -> new RuntimeException("Unsupported User Type value: " + value));
  }

  public String getValue() {
    return value;
  }
}
