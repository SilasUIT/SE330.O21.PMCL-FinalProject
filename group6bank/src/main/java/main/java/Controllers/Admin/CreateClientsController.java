package main.java.Controllers.Admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.connect.Models.Clients;
import main.connect.Repository.ClientsRepo;
import main.java.GlobalDataAdmin;

public class CreateClientsController implements Initializable {

    public TextField fName_fld;
    public TextField lName_fld;
    public TextField password_fld;
    public TextField payeeAddress_fld;
    public Label pAddress_lbl;
    public CheckBox ch_acc_box;
    public TextField ch_amount_fld;
    public CheckBox sv_acc_box;
    public TextField sv_amount_fld;
    public Button create_client_btn;
    public Label error_lbl;
    public Label lbl_password_check;
    public Label lbl_payeeAddress_check;
    final ClientsRepo clientsRepo = new ClientsRepo();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        create_client_btn.setOnAction(e -> CreateClientEvent());
        clientsRepo.getListClients();
        lbl_password_check.setVisible(false);
        lbl_payeeAddress_check.setVisible(false);
        checkPassword();
    }

    void checkPassword() {
        password_fld.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                lbl_password_check.setText(clientsRepo.checkPassword(newValue));
                lbl_password_check.setVisible(true);
            }
        });
    }

    void CreateClientEvent() {

        float svMoney = 0;
        float ckMoney = 0;
        if (fName_fld.getText().isBlank() == true || lName_fld.getText().isBlank() == true
                || password_fld.getText().isBlank() == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("3 trường đầu tiên không được trống");
            alert.showAndWait();
            return;

        }
        if (sv_acc_box.isSelected() == true) {
            try {
                svMoney = Integer.valueOf(sv_amount_fld.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("tiền phải là số nguyên dương");
                alert.showAndWait();
                return;
            }

        }
        if (sv_acc_box.isSelected() == true) {
            try {
                ckMoney = Integer.valueOf(ch_amount_fld.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("tiền phải là số nguyên dươngà ");
                alert.showAndWait();
                return;
            }
        }
        String response = clientsRepo.addUser(fName_fld.getText(), lName_fld.getText(), payeeAddress_fld.getText(),
                password_fld.getText(),
                ckMoney, svMoney);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(response);
        alert.showAndWait();
    }

}
