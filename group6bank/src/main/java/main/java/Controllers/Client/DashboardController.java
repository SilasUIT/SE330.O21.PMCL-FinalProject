package main.java.Controllers.Client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.connect.Models.CheckingAccount;
import main.connect.Models.Clients;
import main.connect.Models.SavingAccount;
import main.connect.Repository.TransactionRepo;
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
    TransactionRepo transactionRepo = new TransactionRepo();

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

        transactionRepo.GetListTransaction(client.getId());
        send_money_btn.setOnAction((e) -> send_money_btn_event_click());
    }

    void send_money_btn_event_click() {
        try {
            float amount = Float.valueOf(amount_fld.getText());
            if (amount <= 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Số tiền không được <0");
                alert.showAndWait();
                return;
            }
            if (payee_fld.getText().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Người dùng không được trống");
                alert.showAndWait();
                return;
            }
            String response = transactionRepo.addTransation(GlobalData.getInstance().getClient().getId(),
                    payee_fld.getText(), amount, "29/01/2003", message_fld.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(response);
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Số tiền phải là số nguyên");
            alert.showAndWait();
        }
    }

}
