package main.java.Controllers.Admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.connect.Repository.ClientsRepo;

public class CreateClientsController implements Initializable {

    public TextField name_fld;
    public TextField password_fld;
    public TextField pAddress_fld;
    public CheckBox pAddress_box;
    public Label pAddress_lbl;
    public CheckBox ch_acc_box;
    public TextField ch_amount_fld;
    public CheckBox sv_acc_box;
    public TextField sv_amount_fld;
    public Button create_client_btn;
    public Label error_lbl;
    final ClientsRepo clientsRepo = new ClientsRepo();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        create_client_btn.setOnAction(e -> CreateClientEvent());
        clientsRepo.getListClients();
    }

    void CreateClientEvent() {

        float svMoney = 0;
        float ckMoney = 0;
        if (name_fld.getText().isBlank() == true || pAddress_fld.getText().isBlank() == true
                || password_fld.getText().isBlank() == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("3 trường đầu tiên không được trống");
            alert.showAndWait();
            return;

        }
        if (sv_acc_box.isSelected() == true) {
            try {
                svMoney = Float.valueOf(sv_amount_fld.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("tiền phải là số");
                alert.showAndWait();
                return;
            }

        }
        if (sv_acc_box.isSelected() == true) {
            try {
                ckMoney = Float.valueOf(ch_amount_fld.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("tiền phải là số");
                alert.showAndWait();
                return;
            }
        }
        String response = clientsRepo.addUser(name_fld.getText(), pAddress_fld.getText(), password_fld.getText(),
                ckMoney, svMoney);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(response);
        alert.showAndWait();
    }

}
