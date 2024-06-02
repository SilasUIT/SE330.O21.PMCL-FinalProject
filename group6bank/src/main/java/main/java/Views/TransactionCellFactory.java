package main.java.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import main.java.Controllers.Client.TransactionCellController;
import main.java.Models.Transaction;

public class TransactionCellFactory extends ListCell<Transaction>{
    @Override
    protected void updateItem(Transaction transaction, boolean empty){
        super.updateItem(transaction, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/TransactionCell.fxml")); 
            TransactionCellController controller = new TransactionCellController();
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
