package com.eva.evaexchangeapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "SHARE")
public class Share {

  @Id
  @Column(name = "SYMBOL", unique = true, length = 3)
  @Pattern(regexp = "^[A-Z]{3}$", message = "Symbol must be exactly 3 uppercase letters")
  private String symbol;

  @Column(name = "PRICE", precision = 10, scale = 2)
  private BigDecimal price;

  @Column(name = "TOTAL_SHARE_QUANTITY")
  private Integer totalShareQuantity;

  @Column(name = "EXCHANGE_CURRENCY")
  private String exchangeCurrency = "USD";

  @Column(name = "UPDATE_AT")
  private LocalDateTime updateAt;

  public Share() {
  }

  public Share(String symbol, BigDecimal price, Integer totalShareQuantity,
      String exchangeCurrency, LocalDateTime updateAt) {
    this.symbol = symbol;
    this.price = price;
    this.totalShareQuantity = totalShareQuantity;
    this.exchangeCurrency = exchangeCurrency;
    this.updateAt = updateAt;
  }

  public @Pattern(regexp = "^[A-Z]{3}$", message = "Symbol must be exactly 3 uppercase letters") String getSymbol() {
    return symbol;
  }

  public void setSymbol(
      @Pattern(regexp = "^[A-Z]{3}$", message = "Symbol must be exactly 3 uppercase letters") String symbol) {
    this.symbol = symbol;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getTotalShareQuantity() {
    return totalShareQuantity;
  }

  public void setTotalShareQuantity(Integer totalShareQuantity) {
    this.totalShareQuantity = totalShareQuantity;
  }

  public String getExchangeCurrency() {
    return exchangeCurrency;
  }

  public void setExchangeCurrency(String exchangeCurrency) {
    this.exchangeCurrency = exchangeCurrency;
  }

  public LocalDateTime getUpdateAt() {
    return updateAt;
  }

  public void setUpdateAt(LocalDateTime updateAt) {
    this.updateAt = updateAt;
  }
}
