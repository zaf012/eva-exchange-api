package com.eva.evaexchangeapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TRADE")
public class Trade {

  @Id
  @Column(name = "ID")
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String id;

  @Column(name = "PORTFOLIO_ID")
  private String portfolioId;

  @Column(name = "SHARE_SYMBOL")
  private String shareSymbol;

  @Column(name = "TRADE_TYPE")
  private String tradeType;

  private BigDecimal quantity;

  @Column(precision = 10, scale = 2)
  private BigDecimal price;

  private LocalDateTime tradeDate;

  public Trade() {
  }

  public Trade(String id, String portfolioId, String shareSymbol, String tradeType,
      BigDecimal quantity, BigDecimal price, LocalDateTime tradeDate) {
    this.id = id;
    this.portfolioId = portfolioId;
    this.shareSymbol = shareSymbol;
    this.tradeType = tradeType;
    this.quantity = quantity;
    this.price = price;
    this.tradeDate = tradeDate;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPortfolioId() {
    return portfolioId;
  }

  public void setPortfolioId(String portfolioId) {
    this.portfolioId = portfolioId;
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

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public LocalDateTime getTradeDate() {
    return tradeDate;
  }

  public void setTradeDate(LocalDateTime tradeDate) {
    this.tradeDate = tradeDate;
  }
}
