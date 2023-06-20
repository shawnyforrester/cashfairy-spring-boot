package com.bnymellon.cashfairy.controller;

import com.bnymellon.cashfairy.model.customer_type;
import lombok.*;

@Data
public class RegisterRequest {

    private String first_name;
    private String last_name;

    private String email;

    private String username;

    private String password;

    private customer_type role;
}
