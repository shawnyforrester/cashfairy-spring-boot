package com.bnymellon.cashfairy.controller;

import com.bnymellon.cashfairy.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials= "")
public class authController {

    private final AuthenticationService service;

    public authController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("registration")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(this.service.registrationService(request));
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> authLogin(
            @RequestBody AuthenticationRequest request
    ){
        return null;
    }
}
