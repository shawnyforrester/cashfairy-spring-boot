package com.bnymellon.cashfairy.service;

import com.bnymellon.cashfairy.dao.AccountsRepository;
import com.bnymellon.cashfairy.exception.AccountNotPersistedException;
import com.bnymellon.cashfairy.exception.UserPaymentDetailsNotFoundException;
import com.bnymellon.cashfairy.model.accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class AccountsService {

    private AccountsRepository aRepo;

    @Autowired
    public AccountsService(AccountsRepository aRepo) {
        this.aRepo = aRepo;
    }

    public accounts getUserAccountDetails(BigInteger customer_id) throws UserPaymentDetailsNotFoundException {
            accounts user_account = this.aRepo.findByOwner(customer_id);
            return user_account;

    }

    /**
     * This method should persist a new payment details account to the database
     * @param account
     * @return
     * @throws AccountNotPersistedException
     */
    public accounts createAccount(accounts account) throws AccountNotPersistedException {
        accounts createdAccount = this.aRepo.save(account);
        return createdAccount;
    }
}
