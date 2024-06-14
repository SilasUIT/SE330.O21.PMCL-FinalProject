package main.java.Controllers.Client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import main.java.Models.Model;

public class ClientController implements Initializable {
    public BorderPane client_parent;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Model.getInstance().getViewFactory().getSelectedMenuItems().addListener((obsvalue, oldval, newval) -> {
            switch (newval) {
                case TRANSACTIONS:
                    client_parent.setCenter(Model.getInstance().getViewFactory().getTransactionsView());
                    break;
                case DASHBOARD:
                    client_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
                    break;
                case ACCOUNTS:
                    client_parent.setCenter(Model.getInstance().getViewFactory().getAccountsView());
                    break;
                default:
                    client_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
                    break;
            }
        });
    }
}
