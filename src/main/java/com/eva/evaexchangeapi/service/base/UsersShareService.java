package com.eva.evaexchangeapi.service.base;

import com.eva.evaexchangeapi.domain.UserShare;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface UsersShareService {


  List<UserShare> getAllUserSharesByPortfolioId(String portfolioId);

  UserShare getUserShareByPortfolioIdAndSymbol(String portfolioId, String shareSymbol);

  @Transactional
  void updateShare(String portfolioId, String shareSymbol, BigDecimal amount);
}
