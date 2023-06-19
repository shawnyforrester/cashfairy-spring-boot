package com.bnymellon.cashfairy.exception;


public class UserPaymentDetailsNotFoundException extends Exception{

    private String message;
    public UserPaymentDetailsNotFoundException() {
        this.message = "The account for this user has not been found";
    }
}
