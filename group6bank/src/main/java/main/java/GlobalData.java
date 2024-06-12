package main.java;

import main.connect.Models.Clients;

public class GlobalData {

    private static GlobalData instance;
    private Clients client;

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
}
