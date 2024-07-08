package com.eva.evaexchangeapi.controller.request;

import java.math.BigDecimal;

public class TradeRequest {

  private String userId;

  private String shareSymbol;

  private String tradeType;

  private BigDecimal quantity;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getShareSymbol() {
    return shareSymbol;
  }

  public void setShareSymbol(String shareSymbol) {
    this.shareSymbol = shareSymbol;
  }

  public String getTradeType() {
    return tradeType;
  }

  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }
}
