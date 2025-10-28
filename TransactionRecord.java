package com.example.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class TransactionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int fromAccountId;
    private int toAccountId;
    private double amount;
    private Date date;

    public TransactionRecord() {}

    public TransactionRecord(int fromAccountId, int toAccountId, double amount) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.date = new Date();
    }
}
