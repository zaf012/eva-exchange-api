package com.eva.evaexchangeapi.service;

import com.eva.evaexchangeapi.domain.UserShare;
import com.eva.evaexchangeapi.repository.UserShareRepository;
import com.eva.evaexchangeapi.service.base.UsersShareService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersShareServiceImpl implements UsersShareService {

  private final UserShareRepository userShareRepository;

  public UsersShareServiceImpl(UserShareRepository userShareRepository) {
    this.userShareRepository = userShareRepository;
  }

  @Override
  public List<UserShare> getAllUserSharesByPortfolioId(String portfolioId) {
    return userShareRepository.findAllByPortfolioId(portfolioId);
  }


  @Override
  public UserShare getUserShareByPortfolioIdAndSymbol(String portfolioId, String shareSymbol) {
    return userShareRepository.findByPortfolioIdAndShareSymbol(portfolioId,
        shareSymbol).orElse(new UserShare());
  }

  @Transactional
  @Override
  public void updateShare(String portfolioId, String shareSymbol, BigDecimal amount) {

    UserShare userShareByPortfolioIdAndSymbol = this.getUserShareByPortfolioIdAndSymbol(portfolioId,
        shareSymbol);

    if (Objects.isNull(userShareByPortfolioIdAndSymbol.getId())) {
      if (amount.compareTo(BigDecimal.ZERO) > 0) {
        userShareByPortfolioIdAndSymbol.setPortfolioId(portfolioId);
        userShareByPortfolioIdAndSymbol.setShareSymbol(shareSymbol);
        userShareByPortfolioIdAndSymbol.setQuantity(amount);

        userShareRepository.save(userShareByPortfolioIdAndSymbol);
      } else {
        throw new RuntimeException("No share found for portfolioId to sell :" + portfolioId);
      }
    } else {
      if (amount.multiply(new BigDecimal(-1))
          .compareTo(userShareByPortfolioIdAndSymbol.getQuantity()) > 0) {
        throw new RuntimeException("Amount is bigger than portfolio quantity" + amount);
      }
      userShareByPortfolioIdAndSymbol.setQuantity(
          userShareByPortfolioIdAndSymbol.getQuantity().add(amount));
      userShareRepository.save(userShareByPortfolioIdAndSymbol);
    }

  }


}
