package com.eva.evaexchangeapi.service;

import com.eva.evaexchangeapi.controller.request.UsersCreateRequest;
import com.eva.evaexchangeapi.domain.Users;
import com.eva.evaexchangeapi.repository.UserRepository;
import com.eva.evaexchangeapi.service.base.BalanceService;
import com.eva.evaexchangeapi.service.base.PortfolioService;
import com.eva.evaexchangeapi.service.base.UserService;
import java.util.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BalanceService balanceService;
  private final PortfolioService portfolioService;

  public UserServiceImpl(UserRepository userRepository, BalanceService balanceService,
      PortfolioService portfolioService) {
    this.userRepository = userRepository;
    this.balanceService = balanceService;
    this.portfolioService = portfolioService;
  }

  @Override
  public Users findUserByUserName(String userName) {

    return userRepository.findByUserName(userName)
        .orElseThrow(() -> new IllegalArgumentException("Invalid username!"));
  }

  @Override
  public Users findUserById(String id) {

    return userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user ID!"));
  }

  @Transactional
  @Override
  public void createUser(UsersCreateRequest request) {

    Users users = new Users();

    users.setUserName(request.getUserName());
    users.setPassword(Base64.getEncoder().encodeToString(request.getPassword().getBytes()));
    users.setUserType(request.getUserType());

    Users savedUser = userRepository.saveAndFlush(users);

    portfolioService.createPortfolio(savedUser.getId());
    balanceService.createBalance(savedUser.getId());

  }

  @Override
  public String getUserIdByUsernameAndPassword(String userName, String password) {

    Users userByUserName = this.findUserByUserName(userName);

    byte[] strAsByte = Base64.getDecoder().decode(userByUserName.getPassword());
    String decodedPassword = new String(strAsByte);
    if (password.equals(decodedPassword)) {
      return userByUserName.getId();
    } else {
      throw new IllegalArgumentException("Invalid password!");
    }

  }


}
