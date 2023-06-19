package com.bnymellon.cashfairy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;


/**
 * This class describes the transactions entity that will store all transactions for each sending user and receiving user
 * The table representing this class will have a foreign key referencing the User ID in the customer
 * table directly.
 */
@Entity
@Data
@Table(name= "transactions")
public class transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger transaction_id;

    @Column(name = "sender_id")
    private BigInteger sender_id;

    @Column(name = "receiver_id")
    private BigInteger receiver_id;

    @Column(name = "transaction_date")
    private Date date;

    @Column(name = "transactions_amount")
    private float transaction_amount;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonBackReference
    private customer transaction_owner;


}
