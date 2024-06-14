package main.java.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.connect.Models.Clients;
import main.connect.Repository.ClientsRepo;
import main.java.GlobalData;
import main.java.Models.Model;
import main.java.Views.AccountType;
import javafx.scene.input.KeyCode;

public class LoginController implements Initializable {
    public ChoiceBox<String> acc_selector;
    public Label payee_address_lbl;
    public TextField payee_address_fld;
    public TextField password_fld;
    public Button login_btn;
    public Label error_lbl;
    final ClientsRepo clientRepo = new ClientsRepo();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        acc_selector.setItems(
                FXCollections.observableArrayList(AccountType.CLIENT.toString(), AccountType.ADMIN.toString()));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccoutnType().toString());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory()
                .setLoginAccoutnType(AccountType.fromString(acc_selector.getValue())));
        login_btn.setOnAction(e -> onlogin());

        // Set up Enter key event for payee_address_fld
        payee_address_fld.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onlogin();
            }
        });

        // Set up Enter key event for password_fld
        password_fld.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onlogin();
            }
        });
    }

    public void onlogin() {
        AccountType selectedType = AccountType.fromString(acc_selector.getValue());
        Stage stage = (Stage) error_lbl.getScene().getWindow();

        if (selectedType == AccountType.CLIENT) {
            try {
                if (clientRepo.Authentic(payee_address_fld.getText(), password_fld.getText())) {
                    Model.getInstance().getViewFactory().closeStage(stage);
                    Model.getInstance().getViewFactory().showClientWindow();
                } else {
                    error_lbl.setText("Sai tài khoản hoặc mật khẩu");
                }
            } catch (Exception e) {
                error_lbl.setText("Đăng nhập thất bại");
            }

        } else {
            if (clientRepo.AuthenticWithAdmin(payee_address_fld.getText(), password_fld.getText())) {
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showAdminWindow();
            } else {
                error_lbl.setText("Sai tài khoản hoặc mật khẩu");
            }
        }
    }
}