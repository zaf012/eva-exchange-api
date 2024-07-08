package com.eva.evaexchangeapi.controller.request;

import jakarta.validation.constraints.NotNull;

public class UsersCreateRequest {

  @NotNull
  private String userName;

  @NotNull
  private String password;

  @NotNull
  private String userType = "TRADER";

  public @NotNull String getUserName() {
    return userName;
  }

  public void setUserName(@NotNull String userName) {
    this.userName = userName;
  }

  public @NotNull String getPassword() {
    return password;
  }

  public void setPassword(@NotNull String password) {
    this.password = password;
  }

  public @NotNull String getUserType() {
    return userType;
  }

  public void setUserType(@NotNull String userType) {
    this.userType = userType;
  }
}
