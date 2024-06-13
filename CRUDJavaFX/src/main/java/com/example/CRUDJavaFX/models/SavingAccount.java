package com.example.CRUDJavaFX.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("SavingAccount")
public class SavingAccount {
    @Id
    String id;

    String idOwner;
    String accountNumber;
    float withDrawLimit;
    float balance;


    public SavingAccount(String idOwner, String accountNumber, float withDrawLimit, float balance) {
        this.idOwner = idOwner;
        this.accountNumber = accountNumber;
        this.withDrawLimit = withDrawLimit;
        this.balance = balance;
    }

    public SavingAccount() {
    }

    public SavingAccount(String id, String idOwner, String accountNumber, float withDrawLimit, float balance) {
        this.id = id;
        this.idOwner = idOwner;
        this.accountNumber = accountNumber;
        this.withDrawLimit = withDrawLimit;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getWithDrawLimit() {
        return withDrawLimit;
    }

    public void setWithDrawLimit(float withDrawLimit) {
        withDrawLimit = withDrawLimit;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
