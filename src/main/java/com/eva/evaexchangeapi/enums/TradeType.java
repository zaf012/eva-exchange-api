package com.eva.evaexchangeapi.enums;

import java.util.HashMap;

import java.util.Map;
import java.util.Optional;

public enum TradeType {
  BUY("BUY"),
  SELL("SELL");

  private static final Map<String, TradeType> lookup = new HashMap<>();

  static {
    for (TradeType source : TradeType.values()) {
      lookup.put(source.getValue(), source);
    }
  }

  private final String value;

  TradeType(String value) {
    this.value = value;
  }

  public static TradeType findByValue(String value) {
    return Optional.ofNullable(lookup.get(value))
        .orElseThrow(
            () -> new RuntimeException("Unsupported Trade Type value: " + value));
  }

  public String getValue() {
    return value;
  }
}
