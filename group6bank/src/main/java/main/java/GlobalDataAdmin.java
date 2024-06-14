package main.java;

import java.util.List;

import main.connect.Models.Clients;

public class GlobalDataAdmin {
    private static GlobalDataAdmin instance;
    private List<Clients> clients;
    private List<Clients> clientsFound;

    public List<Clients> getClients() {
        return this.clients;
    }

    public void setClients(List<Clients> clients) {
        this.clients = clients;
    }

    public List<Clients> getClientsFound() {
        return this.clientsFound;
    }

    public void setClientsFound(List<Clients> clientsFound) {
        this.clientsFound = clientsFound;
    }

    public static synchronized GlobalDataAdmin getInstance() {
        if (instance == null) {
            instance = new GlobalDataAdmin();
        }
        return instance;
    }

    public void clearAllData() {

        if (this.clients != null) {
            this.clients.clear();
        }
        if (this.clientsFound != null) {
            this.clientsFound.clear();
        }
    }
}
