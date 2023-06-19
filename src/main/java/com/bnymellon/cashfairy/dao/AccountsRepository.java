package com.bnymellon.cashfairy.dao;

import com.bnymellon.cashfairy.model.accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface AccountsRepository extends JpaRepository <accounts, BigInteger> {

    public accounts findByOwner(BigInteger customer_id);

}
