package com.eva.evaexchangeapi.repository;

import com.eva.evaexchangeapi.domain.Trade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, String> {

  List<Trade> findByPortfolioIdAndShareSymbol(String portfolioId, String shareSymbol);


}
