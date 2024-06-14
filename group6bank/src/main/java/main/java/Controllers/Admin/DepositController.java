package main.java.Controllers.Admin;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import main.connect.Models.Clients;
import main.connect.Repository.ClientsRepo;
import main.java.GlobalDataAdmin;

public class DepositController implements Initializable {

    private BorderPane admin_parent;
    public TextField pAddress_fld;
    public Button search_btn;
    public TableView<Clients> tb_Search;
    public TableColumn<Clients, String> cl_name;
    public TableColumn<Clients, String> cl_payeeAddress;
    public TableColumn<Clients, String> cl_dateCreate;
    public TextField ammount_fld;
    public Button deposit_btn;
    public final ClientsRepo clientsRepo = new ClientsRepo();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        search_btn.setOnAction(e -> searchUser());
        deposit_btn.setOnAction(e -> addBalance());
        clientsRepo.getListClientsFound("");
        fetchTableView();
    }

    public void searchUser() {
        clientsRepo.getListClientsFound(pAddress_fld.getText());
        List<Clients> clients = GlobalDataAdmin.getInstance().getClientsFound();

        // Chuyển đổi danh sách thông thường thành ObservableList
        ObservableList<Clients> observableTransactionList = FXCollections.observableArrayList(clients);

        // Gán ObservableList cho ListView
        tb_Search.setItems(observableTransactionList);
    }

    public void addBalance() {
        try {
            float amount = Float.parseFloat(ammount_fld.getText());
            String response = clientsRepo.AddBalane(tb_Search.getSelectionModel().getSelectedItem().getId(), amount);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(response);
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Bạn chưa chọn người dùng hoặc số tiền bị sai ");
            alert.showAndWait();
        }

    }

    public void fetchTableView() {
        if (!GlobalDataAdmin.getInstance().getClientsFound().isEmpty()) {

            List<Clients> clients = GlobalDataAdmin.getInstance().getClientsFound();

            // Chuyển đổi danh sách thông thường thành ObservableList
            ObservableList<Clients> observableTransactionList = FXCollections.observableArrayList(clients);

            // Gán ObservableList cho ListView
            tb_Search.setItems(observableTransactionList);
            cl_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            cl_payeeAddress.setCellValueFactory(new PropertyValueFactory<>("payeeAdress"));
            cl_dateCreate.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        }
    }

}
