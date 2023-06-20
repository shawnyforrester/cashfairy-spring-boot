package com.bnymellon.cashfairy.config;

import com.bnymellon.cashfairy.dao.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * This class checks that a customer attempting to log in
 * is filtered for a valid token
 */

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    private final UserDetailsService us;

    private final TokenRepository tokenRepository;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService us, TokenRepository tokenRepository) {
        this.jwtService = jwtService;
        this.us = us;
        this.tokenRepository = tokenRepository;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        if(request.getServletPath().contains("/")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userName;
        jwt = authHeader.substring(7);
        //after extracting the token we now need to extract the username
        //we need a class that can extract the username from the jwt
        userName = jwtService.extractUserName(jwt);
        if(userName !=null && SecurityContextHolder.getContext().getAuthentication() == null ){
            UserDetails userDetails = this.us.loadUserByUsername(userName);
            var ValidToken = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            //checks if the token is still valid
            if(jwtService.isTokenValid(jwt, userDetails) && ValidToken){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)

                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }
        filterChain.doFilter(request, response);
    }
}
