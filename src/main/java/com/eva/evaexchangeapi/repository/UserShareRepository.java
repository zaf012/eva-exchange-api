package com.eva.evaexchangeapi.repository;

import com.eva.evaexchangeapi.domain.UserShare;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserShareRepository extends JpaRepository<UserShare, String> {

  List<UserShare> findAllByPortfolioId(String portfolioId);

  Optional<UserShare> findByPortfolioIdAndShareSymbol(String portfolioId,String shareSymbol);



}
