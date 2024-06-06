package main.java.Models;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {
    private final StringProperty name;
    private final StringProperty payeeAddress;
    private final ObjectProperty<Account> checkingAccount;
    private final ObjectProperty<Account> savingsAccount;
    private final ObjectProperty<LocalDate> dateCreated;

    public Client(String name, String pAddress, Account cAccount, Account sAccount, LocalDate date) {
        this.name = new SimpleStringProperty(this, "name", name);
        this.payeeAddress = new SimpleStringProperty(this, "Payee Address", pAddress);  
        this.checkingAccount = new SimpleObjectProperty<>(this, "Checking Account", cAccount);
        this.savingsAccount = new SimpleObjectProperty<>(this, "Savings Account", sAccount);
        this.dateCreated = new SimpleObjectProperty<>(this, "Date Created", date);
    }

    public StringProperty nameProperty() {
        return this.name;
    }

    public StringProperty pAddressProperty() {
        return this.payeeAddress;
    }

    public ObjectProperty<Account> cAccountProperty() {
        return this.checkingAccount;
    }

    public ObjectProperty<Account> sAccountProperty() {
        return this.savingsAccount;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return this.dateCreated;
    }

    
}
