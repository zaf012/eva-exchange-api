package com.eva.evaexchangeapi.repository;

import com.eva.evaexchangeapi.domain.Share;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepository extends JpaRepository<Share, String> {

  Optional<Share> findBySymbol(String symbol);

}
