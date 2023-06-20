package com.bnymellon.cashfairy.service;

import com.bnymellon.cashfairy.config.JwtService;
import com.bnymellon.cashfairy.controller.AuthenticationRequest;
import com.bnymellon.cashfairy.controller.AuthenticationResponse;
import com.bnymellon.cashfairy.controller.RegisterRequest;
import com.bnymellon.cashfairy.dao.TokenRepository;
import com.bnymellon.cashfairy.dao.customerRep;
import com.bnymellon.cashfairy.model.Token;
import com.bnymellon.cashfairy.model.TokenType;
import com.bnymellon.cashfairy.model.customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {

    private customerRep cRepo;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;

    private final AuthenticationManager authManager;

    public AuthenticationService(PasswordEncoder passwordEncoder, customerRep cRepo, TokenRepository tokenRepository, JwtService jwtService, AuthenticationManager authManager) {
        this.passwordEncoder = passwordEncoder;
        this.cRepo = cRepo;
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }


    /**
     * This endpoint handles login requests
     *
     * @param request
     * @return
     */
    public AuthenticationResponse loginAuthentication(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = cRepo.findByUsername(request.getUsername());
//        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, refreshToken);
        return AuthenticationResponse.builder()
                .refreshToken(refreshToken)
                .build();


    }

    /**
     * This method handles new user registration
     */
    public AuthenticationResponse registrationService(RegisterRequest request) {
        customer newUser = new customer();
        newUser.setFirst_name(request.getFirst_name());
        newUser.setLast_name(request.getLast_name());
        newUser.setEmail(request.getEmail());
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(request.getRole());
        var savedUser = this.cRepo.save(newUser);
        var jwtToken = jwtService.generateToken(newUser);
//        var refreshToken = jwtService.generateRefreshToken(newUser);

        //This user is stored under the token database
       var newToken = saveUserToken(savedUser, jwtToken);
        System.out.print(savedUser);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();

    }

    /**
     * This method saves the user token
     *
     * @param user
     * @param jwtToken
     */
    private Token saveUserToken(customer user, String jwtToken) {
        var token = Token.builder()
                .customer(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
        System.out.print(token);
        return token;
    }

    private void revokeAllUserTokens(customer user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, java.io.IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userName;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userName = jwtService.extractUserName(refreshToken);
        if (userName != null) {
            var user = this.cRepo.findByUsername(userName);

            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }


}
