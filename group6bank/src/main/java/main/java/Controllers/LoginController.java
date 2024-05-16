package main.java.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.Models.Model;

public class LoginController implements Initializable {
    public ChoiceBox<String> acc_selector;
    public Label payee_address_lbl;
    public TextField payee_address_fld;
    public TextField password_fld;
    public Button login_btn;
    public Label error_lbl;


    private String[] choose={"Quản trị viên","Khách hàng"};
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        acc_selector.getItems().addAll(choose);
        login_btn.setOnAction(e->onlogin() );
    }

    public void onlogin()
    {
        Stage stage=(Stage)error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showClientWindow();
    }
}
