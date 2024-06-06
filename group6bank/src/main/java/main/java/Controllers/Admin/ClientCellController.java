package main.java.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.java.Models.Client;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    public Label name_lbl;
    public Label pAddress_lbl;
    public Label ch_acc_lbl;
    public Label sv_acc_lbl;
    public Label date_lbl;
    public Button delete_btn;

    private final Client client;

    public ClientCellController(Client client) {
        this.client = client;
    }

    @Override 
    public void initialize(URL arg0, ResourceBundle arg1) {

    }
}
