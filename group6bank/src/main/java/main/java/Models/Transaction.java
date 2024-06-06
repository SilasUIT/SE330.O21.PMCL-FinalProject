package main.java.Models;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Transaction {
    private final StringProperty sender;
    private final StringProperty receiver;
    private final DoubleProperty amount;
    private final ObjectProperty<LocalDate> date;
    private final StringProperty message;

    public Transaction(String sender, String receiver, double amount, LocalDate date, String message) {
        this.sender = new SimpleStringProperty(this, "sender", sender);
        this.receiver = new SimpleStringProperty(this, "receiver", receiver);
        this.amount = new SimpleDoubleProperty(this, "amount", amount);
        this.date = new SimpleObjectProperty<>(this, "date", date);
        this.message = new SimpleStringProperty(this, "message", message);
    }

    public StringProperty senderProperty() {
        return this.sender;
    }

    public StringProperty receivProperty() {
        return this.receiver;
    }

    public DoubleProperty amoutProperty() {
        return this.amount;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return this.date;
    }

    public StringProperty messageProperty() {
        return this.message;
    }   

}
