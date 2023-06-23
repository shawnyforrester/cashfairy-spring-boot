package com.bnymellon.cashfairy.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

/**
 * This class creates the customer entity which will represent customer objects. The customer table
 * will contain two join columns one that maps to the accounts table and another that maps to the transactions
 * table. The former will be a one-to-one mapping while the latter will be a one-to-many mapping.
 */
@Entity
@Data
@Table(name="customers")
public class customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name = "city")
    private String city;

    @Column(name="email")
    private String email;
    @Column(name = "dob")
    private String dob;
    @Column(name="telephone")
    private String telephone;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private customer_type role;

    @OneToOne(mappedBy = "owner")
    private accounts owner_account;

    @OneToMany
    private List<Token> tokens;

    @OneToMany
    @JsonManagedReference
    private List<transactions> ownerTransactions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername(){
        return this.username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
