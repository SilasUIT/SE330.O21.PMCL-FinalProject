package main.java.Controllers.Client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.GlobalData;
import main.java.Models.Model;
import main.java.Views.ClientMenuOptions;

public class ClientMenuController implements Initializable {
    public Text name_bank;
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        addListeners();
    }

    private void addListeners() {
        dashboard_btn.setOnAction(e -> onDashboard());
        transaction_btn.setOnAction(e -> onTransactions());
        accounts_btn.setOnAction(e -> onAccounts());
        logout_btn.setOnAction(e -> LogOut());
        profile_btn.setOnAction(e -> onProfile());
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getSelectedMenuItems().set(ClientMenuOptions.DASHBOARD);
    }

    private void onTransactions() {
        Model.getInstance().getViewFactory().getSelectedMenuItems().set(ClientMenuOptions.TRANSACTIONS);
    }

    private void onAccounts() {
        Model.getInstance().getViewFactory().getSelectedMenuItems().set(ClientMenuOptions.ACCOUNTS);
    }

    private void onProfile() {
        Model.getInstance().getViewFactory().getSelectedMenuItems().set(ClientMenuOptions.PROFILE);
    }

    private void LogOut() {
        Stage stage = (Stage) name_bank.getScene().getWindow();
        Model.getInstance().getViewFactory().clearCachedViews();
        GlobalData.getInstance().clearAllData();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginPage();
    }

}
