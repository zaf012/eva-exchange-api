package com.eva.evaexchangeapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "USER_SHARE",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"PORTFOLIO_ID", "SHARE_SYMBOL"})})
public class UserShare {

  @Id
  @Column(name = "ID")
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String id;

  @Column(name = "PORTFOLIO_ID")
  private String portfolioId;

  @Column(name = "SHARE_SYMBOL")
  private String shareSymbol;

  @Column(name = "QUANTITY")
  private BigDecimal quantity = BigDecimal.ZERO;

  public UserShare() {
  }

  public UserShare(String id, String portfolioId, String shareSymbol, BigDecimal quantity) {
    this.id = id;
    this.portfolioId = portfolioId;
    this.shareSymbol = shareSymbol;
    this.quantity = quantity;
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

  public BigDecimal getQuantity() {
    return quantity;
  }

  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }
}
