package com.bnymellon.cashfairy.controller;

import com.bnymellon.cashfairy.exception.UserTransactionNotUnsuccessfulException;
import com.bnymellon.cashfairy.model.transactions;
import com.bnymellon.cashfairy.service.TransactionService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class transactionsController {

    private TransactionService service;

    public transactionsController(TransactionService service) {
        this.service = service;
    }

    /**
     * This method should create a new transaction
     *
     * @param transaction
     * @return
     */
    @PostMapping("profile/{user_id}/transaction")
    public ResponseEntity createTransaction(@RequestBody transactions transaction) {
        try {
            transactions newTransaction = this.service.createNewTransaction(transaction);
            if (newTransaction != null) {
                return ResponseEntity.ok("Transaction Successfully created");
            }
        } catch (UserTransactionNotUnsuccessfulException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Transaction unsuccessful");

        }
        return null;
    }


}







