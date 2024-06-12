package main.java.Models;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import main.java.Views.AccountType;
import main.java.Views.ViewFactory;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;


    //Client Data Section
    private Client client;
    private boolean clientLoggedInFlag;
    private AccountType loginAccountType = AccountType.CLIENT;
    //Admin Data Section

    private Model() {
        this.viewFactory= new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.client = new Client(null, null, null, null, null);
    }

    public static synchronized Model getInstance(){
        if(model==null)
        {
            model=new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccoutnType(AccountType loginAccountType){
        this.loginAccountType=loginAccountType;
    }
    /*
     * Client Method Section
     */

    public boolean getClientLoggedInFlag() {
        return this.clientLoggedInFlag;
    }
    
    public void setClientLoggedInFlag(boolean flag) {
        this.clientLoggedInFlag=flag;
    }

    public Client getClient() {
        return client;
    }

    public boolean evaluateClientCred(String pAddress, String password) {
        try {
            if (databaseDriver.authenticateUser(pAddress, password) == true) {
                System.out.println("User authenticated successfully.");
                return true; // Xác thực thành công
            } else {
                System.out.println("Authentication failed. User not found.");
                return false; // Xác thực thất bại
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Xử lý ngoại lệ
        }
    }
}
