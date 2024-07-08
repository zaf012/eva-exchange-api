package com.eva.evaexchangeapi.repository;

import com.eva.evaexchangeapi.domain.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

  Optional<Users> findByUserName(String userName);




}
