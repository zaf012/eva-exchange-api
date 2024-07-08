package com.eva.evaexchangeapi.controller.request;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class BalanceRequest {

  @NotNull
  private BigDecimal amount;
  @NotNull
  private String userId;

  private String currency = "USD";

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
}
