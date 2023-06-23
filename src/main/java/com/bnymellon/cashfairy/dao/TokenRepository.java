package com.bnymellon.cashfairy.dao;

import com.bnymellon.cashfairy.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, BigInteger> {

    @Query(value = """
      select t from Token t inner join customer u\s
      on t.customer.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(BigInteger id);

    Optional<Token> findByToken(String token);
}
