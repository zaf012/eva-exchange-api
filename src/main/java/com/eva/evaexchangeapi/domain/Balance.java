package com.eva.evaexchangeapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "BALANCE")
public class Balance {


  @Id
  @Column(name = "ID")
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String id;

  @Column(name = "USER_ID")
  private String userId;

  @Column(name = "CURRENCY")
  private String currency = "USD";

  @Column(name = "CURRENT_BALANCE")
  private BigDecimal currentBalance;

  public Balance() {
  }

  public Balance(String userId, String currency, BigDecimal currentBalance) {
    this.userId = userId;
    this.currency = currency;
    this.currentBalance = currentBalance;
  }

  public Balance(String id, String userId, String currency, BigDecimal currentBalance) {
    this.id = id;
    this.userId = userId;
    this.currency = currency;
    this.currentBalance = currentBalance;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public BigDecimal getCurrentBalance() {
    return currentBalance;
  }

  public void setCurrentBalance(BigDecimal currentBalance) {
    this.currentBalance = currentBalance;
  }
}
