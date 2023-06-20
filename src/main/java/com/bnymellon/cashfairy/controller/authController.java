package com.bnymellon.cashfairy.controller;

import com.bnymellon.cashfairy.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller class should handle registration of a new user and
 * authenticate existing users. A new jwtoken should be generated for each new user.
 */
@RestController
@RequestMapping("/")
public class authController {

    private final AuthenticationService service;

    public authController(AuthenticationService service) {
        this.service = service;
    }

    @CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(this.service.registrationService(request));
    }

    @CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authLogin(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(this.service.loginAuthentication(request));
    }
}
