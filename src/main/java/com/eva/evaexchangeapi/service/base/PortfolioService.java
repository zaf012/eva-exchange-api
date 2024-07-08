package com.eva.evaexchangeapi.service.base;

import com.eva.evaexchangeapi.domain.Portfolio;
import com.eva.evaexchangeapi.domain.UserShare;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public interface PortfolioService {

  Portfolio findPortfolioById(String id);

  Portfolio findPortfolioByUserId(String userId);

  Portfolio findPortfolioByUserName(String userName);

  @Transactional
  void createPortfolio(String userId);

  List<UserShare> getAllUserSharesByUserName(String userName);

  List<UserShare> getAllUserSharesByUserId(String userId);

  UserShare getUserShareByUserName(String userName, String shareSymbol);

  UserShare getUserShareByUserId(String userId, String shareSymbol);

  @org.springframework.transaction.annotation.Transactional
  void updateUserShare(String userId, String shareSymbol, BigDecimal amount);
}
