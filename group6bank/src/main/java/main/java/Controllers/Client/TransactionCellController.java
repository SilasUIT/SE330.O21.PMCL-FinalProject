package main.java.Controllers.Client;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import main.java.Models.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;    

public class TransactionCellController implements Initializable{

    public FontAwesomeIcon in_Icon;
    public  FontAwesomeIcon out_Icon;
    public Label trans_date_lbl;
    public Label send_lbl;
    public Label recieve_lbl;
    public Label amount_lbl;

    private final Transaction transaction;

    public TransactionCellController (Transaction transaction){
        this.transaction = transaction;
    }

    @Override
    public void initialize(URL url, ResourceBundle ResourceBundle) {
        // TODO Auto-generated method stub
        
    }
}
