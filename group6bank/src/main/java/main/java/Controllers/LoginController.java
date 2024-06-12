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
            // Đánh giá thông tin đăng nhập của khách hàng
            isAuthenticated = Model.getInstance().evaluateClientCred(payee_address_fld.getText(), password_fld.getText());
        } else {
            Model.getInstance().getViewFactory().showAdminWindow();
            isAuthenticated = true; // Giả sử xác thực thành công cho admin để mở cửa sổ admin
        }
    
        if (isAuthenticated) {
            // Đóng cửa sổ đăng nhập hiện tại
            Model.getInstance().getViewFactory().closeStage(stage);
            // Hiển thị cửa sổ mới tùy thuộc vào loại tài khoản
            if (selectedType == AccountType.CLIENT) {
                Model.getInstance().getViewFactory().showClientWindow(); // Thay `showClientWindow` bằng phương thức thực tế để hiển thị cửa sổ của khách hàng
            }
            // Không cần else vì trường hợp admin đã được xử lý ở trên
        } else {
            // Hiển thị thông báo lỗi nếu xác thực thất bại
            error_lbl.setText("Authentication failed. Please try again.");
        }
    }
}