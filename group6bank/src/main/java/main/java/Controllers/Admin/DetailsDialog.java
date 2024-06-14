package main.java.Controllers.Admin;

import java.text.DecimalFormat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.connect.Models.CheckingAccount;
import main.connect.Models.SavingAccount;

public class DetailsDialog extends Dialog<Void> {

    public DetailsDialog(SavingAccount savingAccount, CheckingAccount checkingAccount) {
        setTitle("Chi tiết tài khoản");
        setHeaderText("Thông tin chi tiết tài khoản");

        // Set the button types.
        ButtonType closeButtonType = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(closeButtonType);

        // Create the labels and values.
        HBox content = new HBox(20);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);

        DecimalFormat formatter = new DecimalFormat("#,###");

        if (savingAccount != null) {
            VBox savingBox = createAccountBox("Tài khoản tiết kiệm", savingAccount.getAccountNumber(),
                    formatter.format(savingAccount.getBalance()));
            content.getChildren().add(savingBox);
        }
        
        if (checkingAccount != null) {
            VBox checkingBox = createAccountBox("Tài khoản thanh toán", checkingAccount.getAccountNumber(),
                    formatter.format(checkingAccount.getBalance()));
            content.getChildren().add(checkingBox);
        }
        
        getDialogPane().setContent(content);
        
    }

    private VBox createAccountBox(String accountType, String accountNumber, String string) {
        Label typeLabel = new Label(accountType);
        typeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Label accountNumberLabel = new Label("Số tài khoản:");
        Label accountNumberValue = new Label(accountNumber);
        HBox accountNumberBox = new HBox(10, accountNumberLabel, accountNumberValue);

        Label balanceLabel = new Label("Số dư:");
        Label balanceValue = new Label(String.valueOf(string));
        HBox balanceBox = new HBox(10, balanceLabel, balanceValue);

        VBox accountBox = new VBox(10, typeLabel, accountNumberBox, balanceBox);
        accountBox.setPadding(new Insets(10));
        accountBox.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-border-radius: 5;");

        return accountBox;

    }
}