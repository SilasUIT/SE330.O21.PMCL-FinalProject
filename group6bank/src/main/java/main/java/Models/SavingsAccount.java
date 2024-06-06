package main.java.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


public class SavingsAccount extends Account{
    private final DoubleProperty withdrawlLimit;

    public SavingsAccount(String owner, String accountNumber, double balance, double wLimit) {
        super(owner, accountNumber, balance);
        this.withdrawlLimit = new SimpleDoubleProperty(this, "Withdrawl Limit", wLimit);
    }

    public DoubleProperty withdrawlLimitProp() {
        return withdrawlLimit;
    }

}
