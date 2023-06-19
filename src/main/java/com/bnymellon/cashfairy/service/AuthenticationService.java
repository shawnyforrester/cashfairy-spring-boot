package com.bnymellon.cashfairy.service;

import com.bnymellon.cashfairy.config.JwtService;
import com.bnymellon.cashfairy.controller.AuthenticationRequest;
import com.bnymellon.cashfairy.controller.AuthenticationResponse;
import com.bnymellon.cashfairy.controller.RegisterRequest;
import com.bnymellon.cashfairy.dao.customerRep;
import com.bnymellon.cashfairy.model.customer;
import com.bnymellon.cashfairy.model.customer_type;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private customerRep cRepo;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authManager;

    public AuthenticationService(PasswordEncoder passwordEncoder, customerRep cRepo, JwtService jwtService, AuthenticationManager authManager) {
        this.passwordEncoder = passwordEncoder;
        this.cRepo = cRepo;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }


    /**
     * This endpoint handles login requests
     * @param request
     * @return
     */
    public AuthenticationResponse loginAuthentication(AuthenticationRequest request){
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        customer user = cRepo.findByUsername(request.getUsername());
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();


    }

    /**
     * This endpoint handles new user registration
     */
    public AuthenticationResponse registrationService(RegisterRequest request){
        customer newUser = new customer();
        newUser.setFirst_name(request.getFirst_name());
        newUser.setLast_name(request.getLast_name());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(customer_type.ADMIN);
        this.cRepo.save(newUser);
        var jwtToken = jwtService.generateToken(newUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

}
