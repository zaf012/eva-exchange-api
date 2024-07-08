package com.eva.evaexchangeapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PORTFOLIO",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_ID"})})
public class Portfolio {

  @Id
  @Column(name = "ID")
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String id;

  @Column(name = "USER_ID")
  private String userId;

  public Portfolio() {
  }

  public Portfolio(String userId) {
    this.userId = userId;
  }

  public Portfolio(String id, String userId) {
    this.id = id;
    this.userId = userId;
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
}
