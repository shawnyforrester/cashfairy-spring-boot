package com.bnymellon.cashfairy.controller;

import lombok.*;

@Data
public class RegisterRequest {

    private String first_name;
    private String last_name;

    private String email;

    private String username;

    private String password;
}
