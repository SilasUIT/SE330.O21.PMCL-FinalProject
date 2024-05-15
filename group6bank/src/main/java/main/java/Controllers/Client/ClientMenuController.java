package main.java.Controllers.Client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import main.java.Models.Model;

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
        System.out.println("abc");
        addListeners();
    }

    private void addListeners() {
        dashboard_btn.setOnAction(e -> onDashboard());
        transaction_btn.setOnAction(e -> onTransactions());
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getSelectedMenuItems().set("Dashboard");
    }

    private void onTransactions() {
        Model.getInstance().getViewFactory().getSelectedMenuItems().set("Transactions");
    }
}
