package com.bnymellon.cashfairy.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.internal.build.AllowPrintStacktrace;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthenticationResponse {

    private String token;
}
