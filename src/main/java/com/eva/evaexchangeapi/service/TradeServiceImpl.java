package com.eva.evaexchangeapi.service;

import com.eva.evaexchangeapi.controller.request.TradeRequest;
import com.eva.evaexchangeapi.domain.Balance;
import com.eva.evaexchangeapi.domain.Portfolio;
import com.eva.evaexchangeapi.domain.Share;
import com.eva.evaexchangeapi.domain.Trade;
import com.eva.evaexchangeapi.domain.UserShare;
import com.eva.evaexchangeapi.enums.TradeType;
import com.eva.evaexchangeapi.repository.TradeRepository;
import com.eva.evaexchangeapi.service.base.BalanceService;
import com.eva.evaexchangeapi.service.base.PortfolioService;
import com.eva.evaexchangeapi.service.base.ShareService;
import com.eva.evaexchangeapi.service.base.TradeService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TradeServiceImpl implements TradeService {

  private final TradeRepository tradeRepository;
  private final PortfolioService portfolioService;
  private final ShareService shareService;
  private final BalanceService balanceService;


  public TradeServiceImpl(TradeRepository tradeRepository, PortfolioService portfolioService,
      ShareService shareService, BalanceService balanceService) {
    this.tradeRepository = tradeRepository;
    this.portfolioService = portfolioService;
    this.shareService = shareService;
    this.balanceService = balanceService;
  }

  @Override
  @Transactional
  public void executeTrade(TradeRequest tradeRequest) {

    if (Objects.equals(tradeRequest.getTradeType(), TradeType.BUY.getValue())) {
      this.executeBuyTrade(tradeRequest);
    } else if (Objects.equals(tradeRequest.getTradeType(), TradeType.SELL.getValue())) {
      this.executeSellTrade(tradeRequest);
    }
  }


  @Transactional
  public void executeBuyTrade(TradeRequest tradeRequest) {

    Balance balanceByUserId = balanceService.getBalanceByUserId(tradeRequest.getUserId());
    Share shareByShareSymbol = shareService.getShareByShareSymbol(tradeRequest.getShareSymbol());
    Portfolio portfolioByUserId = portfolioService.findPortfolioByUserId(tradeRequest.getUserId());

    BigDecimal cost = tradeRequest.getQuantity().multiply(shareByShareSymbol.getPrice());

    if (new BigDecimal(shareByShareSymbol.getTotalShareQuantity()).compareTo(
        tradeRequest.getQuantity()) < 0) {
      throw new RuntimeException(
          "Invalid share quantity, request quantity is bigger than total share quantity");
    }

    if (cost.compareTo(balanceByUserId.getCurrentBalance()) > 0) {
      throw new RuntimeException("Insufficient funds");
    }

    try {
      Trade trade = new Trade();

      trade.setPortfolioId(portfolioByUserId.getId());
      trade.setShareSymbol(tradeRequest.getShareSymbol());
      trade.setTradeType(TradeType.BUY.getValue());
      trade.setQuantity(tradeRequest.getQuantity());
      trade.setPrice(shareByShareSymbol.getPrice());
      trade.setTradeDate(LocalDateTime.now());

      tradeRepository.save(trade);

      portfolioService.updateUserShare(tradeRequest.getUserId(), tradeRequest.getShareSymbol(),
          tradeRequest.getQuantity());

      balanceByUserId.setCurrentBalance(
          balanceByUserId.getCurrentBalance().add(cost.multiply(new BigDecimal(-1))));
      balanceService.updateCurrentBalance(balanceByUserId);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Transactional
  public void executeSellTrade(TradeRequest tradeRequest) {

    Balance balanceByUserId = balanceService.getBalanceByUserId(tradeRequest.getUserId());
    Share shareByShareSymbol = shareService.getShareByShareSymbol(tradeRequest.getShareSymbol());
    Portfolio portfolioByUserId = portfolioService.findPortfolioByUserId(tradeRequest.getUserId());
    UserShare userShareByUserId = portfolioService.getUserShareByUserId(tradeRequest.getUserId(),
        tradeRequest.getShareSymbol());

    BigDecimal gain = tradeRequest.getQuantity()
        .multiply(shareByShareSymbol.getPrice());

    tradeRequest.setQuantity(tradeRequest.getQuantity().multiply(new BigDecimal(-1)));

    if (Objects.isNull(userShareByUserId.getId())) {
      throw new RuntimeException(
          "User share does not exist");
    }

    if (userShareByUserId.getQuantity().compareTo(
        tradeRequest.getQuantity().multiply(new BigDecimal(-1))) < 0) {
      throw new RuntimeException(
          "Insufficient share quantity to sell, request quantity is bigger than user share quantity");
    }

    try {
      Trade trade = new Trade();

      trade.setPortfolioId(portfolioByUserId.getId());
      trade.setShareSymbol(tradeRequest.getShareSymbol());
      trade.setTradeType(TradeType.SELL.getValue());
      trade.setQuantity(tradeRequest.getQuantity());
      trade.setPrice(shareByShareSymbol.getPrice());
      trade.setTradeDate(LocalDateTime.now());

      tradeRepository.save(trade);

      portfolioService.updateUserShare(tradeRequest.getUserId(), tradeRequest.getShareSymbol(),
          tradeRequest.getQuantity());

      balanceByUserId.setCurrentBalance(
          balanceByUserId.getCurrentBalance().add(gain));
      balanceService.updateCurrentBalance(balanceByUserId);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
