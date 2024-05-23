package main.java.Controllers.Admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import main.java.Models.Model;

public class ClientsController implements Initializable {
    public BorderPane admin_parent;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Model.getInstance().getViewFactory().getSelectedAdminMenuItems().addListener((obsvalue, oldval, newval) -> {
            switch (newval) {
                case CLIENTS:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getadminClientsView());
                    break;
                default:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getadminCreateClientView());
                    break;
            }
        });
    }
    
}
