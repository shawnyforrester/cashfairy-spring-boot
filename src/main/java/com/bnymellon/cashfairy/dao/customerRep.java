package com.bnymellon.cashfairy.dao;

import com.bnymellon.cashfairy.model.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface customerRep extends JpaRepository<customer, Long> {

    public customer findByUsername(String username);
}
