package com.bnymellon.cashfairy.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class AccountExceptionResponse {

    private String response;
    public AccountExceptionResponse() {
        this.response = "Account not persisted";
    }
}
