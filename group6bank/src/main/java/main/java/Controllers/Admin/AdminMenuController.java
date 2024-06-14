package main.java.Controllers.Admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.java.GlobalData;
import main.java.GlobalDataAdmin;
import main.java.Models.Model;
import main.java.Views.AdminMenuOptions;

public class AdminMenuController implements Initializable {
    public Button create_client_btn;
    public Button clients_btn;
    public Button deposit_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        addListener();
    }

    private void addListener() {
        create_client_btn.setOnAction(e -> onClickCreateClient());
        clients_btn.setOnAction(e -> onClickClients());
        deposit_btn.setOnAction(e -> onClickDeposit());
        logout_btn.setOnAction(e -> onClickLogout());
    }

    private void onClickCreateClient() {
        Model.getInstance().getViewFactory().getSelectedAdminMenuItems().set(AdminMenuOptions.CREATE_CLIENT);
    }

    private void onClickClients() {
        Model.getInstance().getViewFactory().getSelectedAdminMenuItems().set(AdminMenuOptions.CLIENTS);
    }

    private void onClickDeposit() {
        Model.getInstance().getViewFactory().getSelectedAdminMenuItems().set(AdminMenuOptions.DEPOSIT);
    }

    private void onClickLogout() {
        GlobalDataAdmin.getInstance().clearAllData();
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginPage();
    }

}
