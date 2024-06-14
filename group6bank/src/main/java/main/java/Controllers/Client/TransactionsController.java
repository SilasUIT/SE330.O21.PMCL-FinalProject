package main.java.Controllers.Client;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.connect.Models.Clients;
import main.connect.Models.Transaction;
import main.connect.Repository.TransactionRepo;
import main.java.GlobalData;
import main.java.GlobalDataAdmin;

public class TransactionsController implements Initializable {

    public TableView<Transaction> tb_view;
    public TableColumn<Transaction, Float> cl_money;
    public TableColumn<Transaction, String> cl_sender;
    public TableColumn<Transaction, String> cl_receiver;
    public TableColumn<Transaction, String> cl_message;
    public TableColumn<Transaction, String> cl_Date;
    public TextField search_fld;
    TransactionRepo transactionRepo = new TransactionRepo();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        fetchData();
        search_fld.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                List<Transaction> transactions = transactionRepo.GetListTransactionFound(newValue);
                System.out.println(transactions.size());
                // Chuyển đổi danh sách thông thường thành ObservableList
                ObservableList<Transaction> observableTransactionList = FXCollections.observableArrayList(transactions);
                // Gán ObservableList cho ListView
                tb_view.setItems(observableTransactionList);
            }
        });
    }

    void fetchData() {
        if (!GlobalData.getInstance().getTransaction().isEmpty()) {

            List<Transaction> transactions = GlobalData.getInstance().getTransaction();

            // Chuyển đổi danh sách thông thường thành ObservableList
            ObservableList<Transaction> observableTransactionList = FXCollections.observableArrayList(transactions);

            // Gán ObservableList cho ListView
            tb_view.setItems(observableTransactionList);
            cl_money.setCellValueFactory(new PropertyValueFactory<>("amount"));
            cl_sender.setCellValueFactory(new PropertyValueFactory<>("addressSender"));
            cl_receiver.setCellValueFactory(new PropertyValueFactory<>("addressReceiver"));
            cl_message.setCellValueFactory(new PropertyValueFactory<>("message"));
            cl_Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        }
    }
}