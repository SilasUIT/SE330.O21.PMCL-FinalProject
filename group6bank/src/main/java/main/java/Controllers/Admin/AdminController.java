package main.java.Controllers.Admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import main.java.Models.Model;

public class AdminController implements Initializable {
    public BorderPane admin_parent; 
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       Model.getInstance().getViewFactory().getSelectedAdminMenuItems().addListener((a,b,value)->{
        System.out.println(value);
        switch(value){
            case "Clients":

            admin_parent.setCenter(Model.getInstance().getViewFactory().getadminClientsView());
            break;
            case "Deposit":

            admin_parent.setCenter(Model.getInstance().getViewFactory().getadminDepositView());
            break;
            default:
            admin_parent.setCenter(Model.getInstance().getViewFactory().getadminCreateClientView());
            break;
        }
       });
    }

}
