package com.eva.evaexchangeapi.service.base;

import com.eva.evaexchangeapi.controller.request.BalanceRequest;
import com.eva.evaexchangeapi.domain.Balance;
import org.springframework.transaction.annotation.Transactional;

public interface BalanceService {

  Balance getBalanceByUserId(String userId);

  @Transactional
  void createBalance(String userId);

  @Transactional
  void updateCurrentBalance(Balance balance);

  @Transactional
  void addDeposit(BalanceRequest request);

  @Transactional
  void drawOut(BalanceRequest request);
}
