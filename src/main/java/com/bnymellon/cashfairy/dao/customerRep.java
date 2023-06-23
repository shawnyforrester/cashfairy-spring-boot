package com.bnymellon.cashfairy.dao;

import com.bnymellon.cashfairy.model.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface customerRep extends JpaRepository<customer, BigInteger> {


    Optional<customer> findByUsername(String username);


}
