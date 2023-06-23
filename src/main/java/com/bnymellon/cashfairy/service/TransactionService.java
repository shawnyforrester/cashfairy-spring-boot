package com.bnymellon.cashfairy.service;

import com.bnymellon.cashfairy.dao.TransactionRepository;
import com.bnymellon.cashfairy.exception.UserTransactionNotUnsuccessfulException;
import com.bnymellon.cashfairy.model.customer;
import com.bnymellon.cashfairy.model.transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class TransactionService {

    private TransactionRepository transRepo;

    @Autowired
    public TransactionService(TransactionRepository transRepo) {
        this.transRepo = transRepo;
    }

    /**
     * This method should persist a new transaction to the database
     */
    public transactions createNewTransaction (transactions transaction) throws UserTransactionNotUnsuccessfulException {
        transactions newTransaction = this.transRepo.save(transaction);
        if(newTransaction != null){
            return newTransaction;
        } return null;
    }

    public List<transactions> getAllTransactions(){
        return transRepo.findAll();
    }
}
