package main.java.Controllers.Client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.connect.Models.CheckingAccount;
import main.connect.Models.SavingAccount;
import main.connect.Repository.ClientsRepo;
import main.java.GlobalData;

public class AccountsController implements Initializable {
    public Label ch_acc_num;
    public Label transaction_limit;
    public Label ch_acc_date;
    public Label ch_acc_bal;
    public Label sv_acc_num;
    public Label withdrawal_limit;
    public Label sv_acc_date;
    public Label sv_acc_bal;
    public TextField amount_to_sv;
    public Button trans_to_sv_btn;
    public TextField amount_to_ch;
    public Button trans_to_cv_btn;

    SavingAccount savingAccount;
    CheckingAccount checkingAccount;
    ClientsRepo clientsRepo = new ClientsRepo();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        checkingAccount = GlobalData.getInstance().getCheckingAccount();
        savingAccount = GlobalData.getInstance().getSavingAccount();
        LoadForm();
    }

    void LoadForm() {
        sv_acc_bal.setText(Float.toString(savingAccount.getBalance()));
        sv_acc_num.setText(savingAccount.getAccountNumber());
        ch_acc_bal.setText(Float.toString(checkingAccount.getBalance()));
        ch_acc_num.setText(checkingAccount.getAccountNumber());
        ch_acc_date.setText(GlobalData.getInstance().getClient().getDateOfBirth());
        sv_acc_date.setText(GlobalData.getInstance().getClient().getDateOfBirth());
        transaction_limit.setText(String.valueOf(checkingAccount.getTransactionAmount()));
        withdrawal_limit.setText(String.valueOf(savingAccount.getWithDrawLimit()));
        trans_to_sv_btn.setOnAction(e -> SavingMoney());
        trans_to_cv_btn.setOnAction(e -> getSavingMoney());
    }

    void SavingMoney() {
        try {

            float amount = Float.valueOf(amount_to_sv.getText());
            if (amount < 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Số tiền không được <0");
                alert.showAndWait();
            } else {
                String response = clientsRepo.eventSavingMoney(amount);
                clientsRepo.getEverything(GlobalData.getInstance().getClient().getId());
                initialize(null, null);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(response);
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Số tiền phải là số nguyên");
            alert.showAndWait();
        }
    }

    void getSavingMoney() {
        try {

            float amount = Float.valueOf(amount_to_ch.getText());
            if (amount < 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Số tiền không được <0");
                alert.showAndWait();
            } else {
                String response = clientsRepo.eventSavingMoney(-amount);
                clientsRepo.getEverything(GlobalData.getInstance().getClient().getId());
                initialize(null, null);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(response);
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setContentText("Số tiền phải là số nguyên");
            alert.showAndWait();
        }
    }
}
