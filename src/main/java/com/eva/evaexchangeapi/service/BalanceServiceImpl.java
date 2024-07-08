package com.eva.evaexchangeapi.service;

import com.eva.evaexchangeapi.controller.request.BalanceRequest;
import com.eva.evaexchangeapi.domain.Balance;
import com.eva.evaexchangeapi.repository.BalanceRepository;
import com.eva.evaexchangeapi.service.base.BalanceService;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BalanceServiceImpl implements BalanceService {

  private final BalanceRepository balanceRepository;


  public BalanceServiceImpl(BalanceRepository balanceRepository) {
    this.balanceRepository = balanceRepository;
  }

  @Override
  public Balance getBalanceByUserId(String userId) {

    return balanceRepository.findByUserId(userId)
        .orElseThrow(() -> new IllegalArgumentException("Balance not found !"));

  }

  @Transactional
  @Override
  public void createBalance(String userId) {

    Balance balance = balanceRepository.findByUserId(userId).orElse(new Balance());

    if (!balance.getId().isBlank()) {
      throw new RuntimeException("Balance already exists");
    }
    balance.setUserId(userId);
    balance.setCurrency("USD");
    balance.setCurrentBalance(BigDecimal.ZERO);

    balanceRepository.save(balance);
  }

  @Transactional
  @Override
  public void updateCurrentBalance(Balance balance) {
    balanceRepository.save(balance);
  }

// para yatırma
  @Transactional
  @Override
  public void addDeposit(BalanceRequest request) {

    Balance balanceByUserId = balanceRepository.findByUserId(request.getUserId())
        .orElseThrow(() -> new RuntimeException("Balance is not exist for requested user!"));

    if (request.getAmount().compareTo(BigDecimal.ZERO) > 0) {
      balanceByUserId.setCurrentBalance(
          balanceByUserId.getCurrentBalance().add(request.getAmount()));

      balanceRepository.save(balanceByUserId);
    } else {
      throw new RuntimeException("Amount is zero or less then zero!");
    }
  }

  // para çekme
  @Transactional
  @Override
  public void drawOut(BalanceRequest request) {

    Balance balanceByUserId = balanceRepository.findByUserId(request.getUserId())
        .orElseThrow(() -> new RuntimeException("Balance is not exist for requested user!"));

    request.setAmount(request.getAmount().multiply(new BigDecimal(-1)));

    if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
      balanceByUserId.setCurrentBalance(
          balanceByUserId.getCurrentBalance().add(request.getAmount()));
      balanceRepository.save(balanceByUserId);
    } else {
      throw new RuntimeException("Out of balance!");
    }

  }

}
