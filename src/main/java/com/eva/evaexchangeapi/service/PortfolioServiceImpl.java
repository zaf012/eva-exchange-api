package com.eva.evaexchangeapi.service;

import com.eva.evaexchangeapi.domain.Portfolio;
import com.eva.evaexchangeapi.domain.UserShare;
import com.eva.evaexchangeapi.repository.PortfolioRepository;
import com.eva.evaexchangeapi.service.base.PortfolioService;
import com.eva.evaexchangeapi.service.base.UsersShareService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PortfolioServiceImpl implements PortfolioService {

  private final PortfolioRepository portfolioRepository;
  private final UsersShareService usersShareService;

  public PortfolioServiceImpl(PortfolioRepository portfolioRepository,
      UsersShareService usersShareService) {
    this.portfolioRepository = portfolioRepository;
    this.usersShareService = usersShareService;
  }

  @Override
  public Portfolio findPortfolioById(String id) {

    return portfolioRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid portfolio ID"));
  }

  @Override
  public Portfolio findPortfolioByUserId(String userId) {

    return portfolioRepository.findByUserId(userId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
  }

  @Override
  public Portfolio findPortfolioByUserName(String userName) {

    return portfolioRepository.findByUserName(userName)
        .orElseThrow(() -> new IllegalArgumentException("Invalid portfolio ID"));
  }

  @Transactional
  @Override
  public void createPortfolio(String userId) {

    Portfolio portfolio = portfolioRepository.findByUserId(userId).orElse(new Portfolio());

    if (!portfolio.getId().isBlank()) {
      throw new RuntimeException("Portfolio already exists");
    }
    portfolio.setUserId(userId);

    portfolioRepository.save(portfolio);
  }

  @Override
  public List<UserShare> getAllUserSharesByUserName(String userName) {
    Portfolio portfolioByUserName = this.findPortfolioByUserName(userName);

    return usersShareService.getAllUserSharesByPortfolioId(portfolioByUserName.getId());
  }

  @Override
  public List<UserShare> getAllUserSharesByUserId(String userId) {
    Portfolio portfolioByUserId = this.findPortfolioByUserId(userId);

    return usersShareService.getAllUserSharesByPortfolioId(portfolioByUserId.getId());
  }

  @Override
  public UserShare getUserShareByUserName(String userName, String shareSymbol) {
    Portfolio portfolioByUserName = this.findPortfolioByUserName(userName);

    return usersShareService.getUserShareByPortfolioIdAndSymbol(portfolioByUserName.getId(),
        shareSymbol);
  }

  @Override
  public UserShare getUserShareByUserId(String userId, String shareSymbol) {
    Portfolio portfolioByUserId = this.findPortfolioByUserId(userId);

    return usersShareService.getUserShareByPortfolioIdAndSymbol(portfolioByUserId.getId(),
        shareSymbol);
  }

  @Transactional
  @Override
  public void updateUserShare(String userId, String shareSymbol, BigDecimal amount) {
    Portfolio portfolioByUserId = this.findPortfolioByUserId(userId);
    usersShareService.updateShare(portfolioByUserId.getId(), shareSymbol, amount);
  }
}
