package main.java.Controllers.Admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import main.java.Models.Model;

public class AdminMenuController implements Initializable {
    public Button create_client_btn;
    public Button clients_btn;
    public Button deposit_btn;
    public Button logout_btn;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) 
    {
       addListener();
    }

    private void addListener()
    {
        create_client_btn.setOnAction(e->onClickCreateClient());
        clients_btn.setOnAction(e->onClickClients());
        deposit_btn.setOnAction(e->onClickDeposit());
    }

    private void onClickCreateClient()
    {
        Model.getInstance().getViewFactory().getSelectedAdminMenuItems().set("CreateClient");
    }
    private void onClickClients()
    {
        Model.getInstance().getViewFactory().getSelectedAdminMenuItems().set("Clients");
    }
    private void onClickDeposit()
    {
        Model.getInstance().getViewFactory().getSelectedAdminMenuItems().set("Deposit");
    }
}
