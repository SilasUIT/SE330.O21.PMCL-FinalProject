package main.java.Controllers.Client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.connect.Models.CheckingAccount;
import main.connect.Models.Clients;
import main.connect.Models.SavingAccount;
import main.java.GlobalData;

public class DashboardController implements Initializable {

    public Text user_name;
    public Label date_login_lbl;
    public Label checking_bal;
    public Label saving_bal;
    public Label checking_acc_num;
    public Label saving_acc_num;
    public Label income_amount;
    public Label expense_amount;
    public ListView transaction_listview;
    public TextField payee_fld;
    public TextField amount_fld;
    public TextArea message_fld;
    public Button send_money_btn;
    private Clients client;
    private SavingAccount savingAccount;
    private CheckingAccount checkingAccount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        client = GlobalData.getInstance().getClient();
        savingAccount = GlobalData.getInstance().getSavingAccount();
        checkingAccount = GlobalData.getInstance().getCheckingAccount();
        LoadForm();
    }

    void LoadForm() {

        user_name.setText(("Chào mừng đã trở lại, " + client.getFirstName() + "" + client.getLastName()));
        saving_bal.setText(Float.toString(savingAccount.getBalance()));
        String lastFourCharsSV = savingAccount.getAccountNumber()
                .substring(savingAccount.getAccountNumber().length() - 4);
        String lastFourCharsCK = checkingAccount.getAccountNumber()
                .substring(checkingAccount.getAccountNumber().length() - 4);
        saving_acc_num.setText(lastFourCharsSV);
        checking_bal.setText(Float.toString(checkingAccount.getBalance()));
        checking_acc_num.setText(lastFourCharsCK);
    }

}
