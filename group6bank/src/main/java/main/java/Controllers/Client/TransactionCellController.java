package main.java.Controllers.Client;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import main.connect.Models.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

public class TransactionCellController implements Initializable {

    public FontAwesomeIcon in_icon;
    public FontAwesomeIcon out_icon;
    public Label trans_date_lbl;
    public Label sender_lbl;
    public Label receiver_lbl;
    public Label amount_lbl;

    private final Transaction transaction;

    public TransactionCellController(Transaction transaction) {
        this.transaction = transaction;

    }

    @Override
    public void initialize(URL url, ResourceBundle ResourceBundle) {
        // TODO Auto-generated method stub

    }

    public void setTransaction(Transaction transaction) {
        trans_date_lbl.setText(transaction.getDate());

        amount_lbl.setText(String.valueOf(transaction.getAmount()));
    }
}
