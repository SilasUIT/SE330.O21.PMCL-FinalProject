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
import main.java.Models.Model;
import main.java.Views.AccountType;

public class LoginController implements Initializable {
    public ChoiceBox<String> acc_selector;
    public Label payee_address_lbl;
    public TextField payee_address_fld;
    public TextField password_fld;
    public Button login_btn;
    public Label error_lbl;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT.toString(), AccountType.ADMIN.toString()));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccoutnType().toString());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccoutnType(AccountType.fromString(acc_selector.getValue())));
        login_btn.setOnAction(e->onlogin() );
    }

    public void onlogin() {
        AccountType selectedType = AccountType.fromString(acc_selector.getValue());
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        boolean isAuthenticated = false;
    
        if (selectedType == AccountType.CLIENT) {
            isAuthenticated = Model.getInstance().evaluateClientCred(payee_address_fld.getText(), password_fld.getText());
        } else {
            Model.getInstance().getViewFactory().showAdminWindow();
            isAuthenticated = true; 
        }
    
        if (isAuthenticated) {
            Model.getInstance().getViewFactory().closeStage(stage);
            if (selectedType == AccountType.CLIENT) {
                Model.getInstance().getViewFactory().showClientWindow();
            }
        } else {
            error_lbl.setText("Xác thực tài khoản thất bại. Vui lòng thử lại.");
        }
    }
}