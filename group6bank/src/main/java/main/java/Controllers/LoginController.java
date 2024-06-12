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
    }

    public void onlogin() {
        AccountType selectedType = AccountType.fromString(acc_selector.getValue());
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        if (selectedType == AccountType.CLIENT) {

            Clients client = clientRepo.Authentic(payee_address_fld.getText(), password_fld.getText());
            if (client != null) {
                GlobalData.getInstance().setClient(client);
                Model.getInstance().getViewFactory().showClientWindow();
            } else {
                error_lbl.setText("Sai tài khoản hoặc mật khẩu");
            }
        } else {
            Model.getInstance().getViewFactory().showAdminWindow();
        }
    }
}