package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ownerName;
    private double balance;

    public Account() {}

    public Account(String ownerName, double balance) {
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public int getId() { return id; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return "Account [id=" + id + ", owner=" + ownerName + ", balance=" + balance + "]";
    }
}
