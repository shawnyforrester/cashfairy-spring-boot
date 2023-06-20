package com.bnymellon.cashfairy.dao;

import com.bnymellon.cashfairy.model.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface customerRep extends CrudRepository<customer, Long> {


    customer findByUsername(String username);


}
