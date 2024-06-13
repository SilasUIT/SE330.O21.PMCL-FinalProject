package com.example.CRUDJavaFX.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("CheckingAccount")
public class CheckingAccount {
    @Id
    String id;

    String idOwner;
    String accountNumber;
    float transactionAmount;
    float balance;

    public CheckingAccount(String id, String idOwner, String accountNumber, float transactionAmount, float balance) {
        this.id = id;
        this.idOwner = idOwner;
        this.accountNumber = accountNumber;
        this.transactionAmount = transactionAmount;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
