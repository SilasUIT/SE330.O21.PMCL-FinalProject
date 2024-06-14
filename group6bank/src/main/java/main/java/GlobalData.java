package main.java;

import main.connect.Models.CheckingAccount;
import main.connect.Models.Clients;
import main.connect.Models.SavingAccount;
import main.connect.Models.Transaction;
import java.util.List;

import javafx.collections.ObservableList;

public class GlobalData {

    private static GlobalData instance;
    private Clients client;
    private SavingAccount savingAccount;
    private CheckingAccount checkingAccount;
    private List<Transaction> transaction;

    public List<Transaction> getTransaction() {
        return this.transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    private GlobalData() {
        // private constructor to prevent instantiation
    }

    public static synchronized GlobalData getInstance() {
        if (instance == null) {
            instance = new GlobalData();
        }
        return instance;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public SavingAccount getSavingAccount() {
        return this.savingAccount;
    }

    public void setSavingAccount(SavingAccount savingAccount) {

        this.savingAccount = savingAccount;
    }

    public CheckingAccount getCheckingAccount() {
        return this.checkingAccount;
    }

    public void setCheckingAccount(CheckingAccount checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public void clearAllData() {
        this.client = null;
        this.savingAccount = null;
        this.checkingAccount = null;
        if (this.transaction != null) {
            this.transaction.clear();
        }
    }

    public void clearAllDataExeptClient() {
        this.savingAccount = null;
        this.checkingAccount = null;
        if (this.transaction != null) {
            this.transaction.clear();
        }
    }

    public ObservableList<Transaction> getTransactionObs() {
        return (ObservableList<Transaction>) transaction;
    }
}
