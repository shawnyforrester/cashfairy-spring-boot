package com.bnymellon.cashfairy.dao;

import com.bnymellon.cashfairy.model.transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TransactionRepository extends JpaRepository <transactions, BigInteger> {


}
