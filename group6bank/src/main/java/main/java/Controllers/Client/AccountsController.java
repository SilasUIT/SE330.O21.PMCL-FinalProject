package main.java.Controllers.Client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.connect.Models.CheckingAccount;
import main.connect.Models.SavingAccount;
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        checkingAccount = GlobalData.getInstance().getCheckingAccount();
        savingAccount = GlobalData.getInstance().getSavingAccount();
        LoadForm();
    }

    void LoadForm() {
        sv_acc_bal.setText(Float.toString(savingAccount.getBalance()));
        sv_acc_num.setText(savingAccount.getAccountNumber());
        ch_acc_bal.setText(Float.toString(savingAccount.getBalance()));
        ch_acc_num.setText(checkingAccount.getAccountNumber());
    }
}
