package com.eva.evaexchangeapi.bootstrap;

import com.eva.evaexchangeapi.repository.BalanceRepository;
import com.eva.evaexchangeapi.repository.PortfolioRepository;
import com.eva.evaexchangeapi.repository.ShareRepository;
import com.eva.evaexchangeapi.repository.UserRepository;
import com.eva.evaexchangeapi.service.base.TradeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class DataBootstrap implements CommandLineRunner {

  private final TradeService tradeService;
  private final ShareRepository shareRepository;
  private final PortfolioRepository portfolioRepository;
  private final UserRepository userRepository;
  private final BalanceRepository balanceRepository;

  public DataBootstrap(TradeService tradeService, ShareRepository shareRepository,
      PortfolioRepository portfolioRepository, UserRepository userRepository,
      BalanceRepository balanceRepository) {
    this.tradeService = tradeService;
    this.shareRepository = shareRepository;
    this.portfolioRepository = portfolioRepository;
    this.userRepository = userRepository;
    this.balanceRepository = balanceRepository;
  }

  @Override
  public void run(String... strings) throws Exception {

    /*shareRepository.saveAllAndFlush(Arrays.asList(
        new Share("ABC", new BigDecimal("100.00"), 250, "USD", LocalDateTime.now()),
        new Share("XYZ", new BigDecimal("50.00"), 300, "USD", LocalDateTime.now()),
        new Share("DEF", new BigDecimal("75.50"), 125, "USD", LocalDateTime.now())));

    List<Users> users = userRepository.saveAllAndFlush(Arrays.asList(
        (new Users("ADMIN", Base64.getEncoder().encodeToString("ADMIN".getBytes()),
            UserType.ADMIN.getValue())),
        new Users("USER1", Base64.getEncoder().encodeToString("USER1".getBytes()),
            UserType.TRADER.getValue()),
        new Users("USER2", Base64.getEncoder().encodeToString("USER2".getBytes()),
            UserType.TRADER.getValue()),
        new Users("USER3", Base64.getEncoder().encodeToString("USER3".getBytes()),
            UserType.TRADER.getValue()),
        new Users("USER4", Base64.getEncoder().encodeToString("USER4".getBytes()),
            UserType.TRADER.getValue()),
        new Users("USER5", Base64.getEncoder().encodeToString("USER5".getBytes()),
            UserType.TRADER.getValue())));

    List<Portfolio> portfolios = new ArrayList<>();
    List<Balance> balances = new ArrayList<>();

    users.forEach(user -> {
      if (UserType.ADMIN.getValue().equals(user.getUserType())) {
        return;
      }
      portfolios.add(new Portfolio(user.getId()));
      balances.add(new Balance(user.getId(), "USD", BigDecimal.ZERO));
    });

    portfolioRepository.saveAllAndFlush(portfolios);
    balanceRepository.saveAllAndFlush(balances);*/

/*    TradeRequest tradeRequest = new TradeRequest();
    tradeRequest.setPortfolioUserName("USER1");
    tradeRequest.setShareSymbol("XYZ");
    tradeRequest.setTradeType("BUY");
    tradeRequest.setQuantity(15);*/

    // tradeService.executeTrade(tradeRequest);

  }
}
