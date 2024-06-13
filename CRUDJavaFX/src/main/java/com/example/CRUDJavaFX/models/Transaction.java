package com.example.CRUDJavaFX.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Transaction")
public class Transaction {
    @Id
    String id;

    String addressSender;
    String addressReceiver;
    float amount;
    String date;
    String message;

    public Transaction(String id, String addressSender, String addressReceiver, float amount, String date, String message) {
        this.id = id;
        this.addressSender = addressSender;
        this.addressReceiver = addressReceiver;
        this.amount = amount;
        this.date = date;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddressSender() {
        return addressSender;
    }

    public void setAddressSender(String addressSender) {
        this.addressSender = addressSender;
    }

    public String getAddressReceiver() {
        return addressReceiver;
    }

    public void setAddressReceiver(String addressReceiver) {
        this.addressReceiver = addressReceiver;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
