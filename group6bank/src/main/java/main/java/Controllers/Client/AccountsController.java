package main.java.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.connect.Models.CheckingAccount;
import main.connect.Models.SavingAccount;
import main.connect.Repository.ClientsRepo;
import main.java.GlobalData;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

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

    private SavingAccount savingAccount;
    private CheckingAccount checkingAccount;
    private ClientsRepo clientsRepo = new ClientsRepo();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loadAccountsData();
        setupButtons();
    }

    private void loadAccountsData() {
        savingAccount = GlobalData.getInstance().getSavingAccount();
        checkingAccount = GlobalData.getInstance().getCheckingAccount();

        DecimalFormat formatter = new DecimalFormat("#,###");

        sv_acc_bal.setText(formatter.format(savingAccount.getBalance()));
        sv_acc_num.setText(savingAccount.getAccountNumber());
        ch_acc_bal.setText(formatter.format(checkingAccount.getBalance()));
        ch_acc_num.setText(checkingAccount.getAccountNumber());
        ch_acc_date.setText(GlobalData.getInstance().getClient().getDateOfBirth());
        sv_acc_date.setText(GlobalData.getInstance().getClient().getDateOfBirth());
        transaction_limit.setText(formatter.format(checkingAccount.getTransactionAmount()));
        withdrawal_limit.setText(formatter.format(savingAccount.getWithDrawLimit()));
    }

    private void setupButtons() {
        trans_to_sv_btn.setOnAction(e -> savingMoney());
        trans_to_cv_btn.setOnAction(e -> withdrawingMoney());
    }

    private void savingMoney() {
        try {
            float amount = Float.parseFloat(amount_to_sv.getText());
            if (amount < 0) {
                showAlert(Alert.AlertType.INFORMATION, "Số tiền không được < 0");
            } else {
                String response = clientsRepo.eventSavingMoney(amount);
                if (response != null && !response.isEmpty()) {
                    showAlert(Alert.AlertType.INFORMATION, response);
                    refreshData();
                }
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Số tiền phải là số nguyên");
        }
    }

    private void withdrawingMoney() {
        try {
            float amount = Float.parseFloat(amount_to_ch.getText());
            if (amount < 0) {
                showAlert(Alert.AlertType.INFORMATION, "Số tiền không được < 0");
            } else {
                String response = clientsRepo.eventSavingMoney(-amount);
                if (response != null && !response.isEmpty()) {
                    showAlert(Alert.AlertType.INFORMATION, response);
                    refreshData();
                }
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Số tiền phải là số nguyên");
        }
    }

    private void refreshData() {
        clientsRepo.getEverything(GlobalData.getInstance().getClient());
        loadAccountsData();
    }

    private void showAlert(Alert.AlertType alertType, String content) {
        Alert alert = new Alert(alertType);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
