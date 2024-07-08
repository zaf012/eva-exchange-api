package com.eva.evaexchangeapi.service.base;

import com.eva.evaexchangeapi.controller.request.UsersCreateRequest;
import com.eva.evaexchangeapi.domain.Users;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

  Users findUserByUserName(String userName);

  Users findUserById(String id);

  @Transactional
  void createUser(UsersCreateRequest request);

  String getUserIdByUsernameAndPassword(String userName, String password);
}
