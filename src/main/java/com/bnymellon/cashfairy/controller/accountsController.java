package com.bnymellon.cashfairy.controller;


import com.bnymellon.cashfairy.exception.AccountNotPersistedException;
import com.bnymellon.cashfairy.exception.UserPaymentDetailsNotFoundException;
import com.bnymellon.cashfairy.model.accounts;
import com.bnymellon.cashfairy.model.customer;
import com.bnymellon.cashfairy.service.AccountsService;
import com.bnymellon.cashfairy.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class accountsController {

    @Autowired
    private AccountsService service;

    @Autowired
    public accountsController(AccountsService service) {
        this.service = service;
    }

    @GetMapping("profile/{user_id}")
    public ResponseEntity getAccountDetailsById(
            @RequestBody customer client
    ) {
        try {
            accounts user_account = this.service.getUserAccountDetails(client.getId());
            return ResponseEntity.ok(user_account);

        } catch (UserPaymentDetailsNotFoundException e) {
            UserPaymentDetailsNotFoundException response = new UserPaymentDetailsNotFoundException();
            e.printStackTrace();
            return ResponseEntity.status(404).body(response);

        }

    }

    @PostMapping("payment-details")
    public ResponseEntity addPaymentDetails(accounts account) {
        try {
            accounts newAccount = this.service.createAccount(account);
            return ResponseEntity.status(200).body("Account successfully created");
        } catch (AccountNotPersistedException e) {
            AccountExceptionResponse response = new AccountExceptionResponse();
            e.printStackTrace();
            return ResponseEntity.status(403).body(response);
        }

    }
}
