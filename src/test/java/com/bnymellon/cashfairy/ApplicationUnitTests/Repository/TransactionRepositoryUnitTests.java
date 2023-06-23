package com.bnymellon.cashfairy.ApplicationUnitTests.Repository;

import com.bnymellon.cashfairy.dao.AccountsRepository;
import com.bnymellon.cashfairy.dao.TransactionRepository;
import com.bnymellon.cashfairy.model.accounts;
import com.bnymellon.cashfairy.model.customer;
import com.bnymellon.cashfairy.model.transactions;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TransactionRepositoryUnitTests {
    @Autowired
    TransactionRepository tRepo;

    @Autowired
    TestEntityManager entityManager;

    /**
     * This test checks to ensure that a new payment object is successfully persisted
     *
     */
    @Test
    public void testAddNewTransaction(){
        transactions transaction = new transactions();
        transaction.setTransaction_amount(800);
        transaction.setDate(new Date());

        entityManager.persistAndFlush(transaction);
        assertThat(tRepo.findAll().stream()
                .filter(entry -> entry.getTransaction_amount() == 800)
                .count() == 1);

    }

    /**
     * This checks that an object is successfully deleted from the database.
     */
    @Test
    public void checkForDeletedEntity(){
        transactions transaction = new transactions();
        transaction.setTransaction_amount(800);
        transaction.setDate(new Date());

        entityManager.persistAndFlush(transaction);
        tRepo.deleteAll();
        assertThat(tRepo.findAll().stream()
                .filter(entry -> entry.getTransaction_amount() == 800)
                .count() == 0);


    }

    /**
     * This test checks to ensure that customers are correctly added to the transaction database
     */
    @Test
    public void checkThatOwnersAreAddedCorrectly(){
        transactions transaction = new transactions();
        transaction.setTransaction_amount(800);
        transaction.setDate(new Date());

        customer newCustomer = new customer();
        newCustomer.setUsername("Sharinghan");
        newCustomer.setFirst_name("Sasuke");
        newCustomer.setLast_name("Uchiha");

        transaction.setTransaction_owner(newCustomer);
        assertThat(tRepo.findAll().stream()
                .filter(entry -> entry.getTransaction_owner().getFirst_name() == "Sasuke")
                .count() == 1);

    }
}
