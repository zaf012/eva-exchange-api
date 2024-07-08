package com.eva.evaexchangeapi.controller.request;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ShareUpdateRequest {
  @NotNull
  private String userId;
  @NotNull
  private String shareSymbol;
  @NotNull
  private BigDecimal rate;

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

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }
}
