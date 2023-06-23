package com.bnymellon.cashfairy.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

/**
 * This class represents the accounts entity which will hold the payment details for each user.
 * It should have a foreign key which references the id column of the customer table.
 */

@Entity
@Data
@Table(name="accounts")
public class accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger account_id;

    @Column(name= "savings_account")
    private String savings_account;

    @Column(name = "checking_account")
    private String checking_account;

    @Column(name="debit_card")
    private String debit_card;

    @Column(name = "credit_card")
    private String credit_card;

    @Column(name = "balance")
    private Double balance;

    @OneToOne(fetch = FetchType.EAGER)
    private customer owner;
}
