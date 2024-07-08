package com.eva.evaexchangeapi.repository;

import com.eva.evaexchangeapi.domain.Portfolio;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, String> {

  @Query(nativeQuery = true, value = """
      select * 
      from portfolio p
        left join user u on u.id = p.user_id     
            where u.username = :username
            """)
  Optional<Portfolio> findByUserName(@Param("username") String username);

  Optional<Portfolio> findByUserId(String userId);

}
