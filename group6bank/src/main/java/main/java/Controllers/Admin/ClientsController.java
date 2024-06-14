package main.java.Controllers.Admin;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import main.connect.Models.CheckingAccount;
import main.connect.Models.Clients;
import main.connect.Repository.CheckingAccountRepo;
import main.connect.Repository.ClientsRepo;
import main.connect.Repository.SavingAccountRepo;
import main.java.GlobalData;
import main.java.GlobalDataAdmin;
import main.java.Controllers.DeleteUserDialog;
import main.java.Models.Model;

public class ClientsController implements Initializable {
    public TextField search_fld;
    public TableView<Clients> tb_Client;
    public TableColumn<Clients, String> cl_name;
    public TableColumn<Clients, String> cl_payeeAddress;
    public TableColumn<Clients, String> cl_dateCreate;
    public Button btn_Del;
    public BorderPane admin_parent;
    public Button btn_View;
    public final ClientsRepo clientsRepo = new ClientsRepo();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Model.getInstance().getViewFactory().getSelectedAdminMenuItems().addListener((obsvalue, oldval, newval) -> {
            switch (newval) {
                case CLIENTS:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getadminClientsView());
                    break;
                default:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getadminCreateClientView());
                    break;
            }
        });

        clientsRepo.getListClientsFound("");
        fetchTableView();
        onChangeSearch();
        btn_Del.setOnAction(e -> onClickDelete());
        btn_View.setOnAction(e -> onClickShowData());
    }

    public void fetchTableView() {
        if (!GlobalDataAdmin.getInstance().getClientsFound().isEmpty()) {

            List<Clients> clients = GlobalDataAdmin.getInstance().getClientsFound();

            // Chuyển đổi danh sách thông thường thành ObservableList
            ObservableList<Clients> observableTransactionList = FXCollections.observableArrayList(clients);

            // Gán ObservableList cho ListView
            tb_Client.setItems(observableTransactionList);
            cl_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            cl_payeeAddress.setCellValueFactory(new PropertyValueFactory<>("payeeAdress"));
            cl_dateCreate.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        }
    }

    public void onChangeSearch() {
        search_fld.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                clientsRepo.getListClientsFound(newValue);
                List<Clients> clients = GlobalDataAdmin.getInstance().getClientsFound();

                // Chuyển đổi danh sách thông thường thành ObservableList
                ObservableList<Clients> observableTransactionList = FXCollections.observableArrayList(clients);

                // Gán ObservableList cho ListView
                tb_Client.setItems(observableTransactionList);
            }
        });
    }

    public void onClickDelete() {
        try {

            DeleteUserDialog dialog = new DeleteUserDialog(
                    tb_Client.getSelectionModel().getSelectedItem().getPayeeAdress());
            dialog.showAndWait().ifPresent(result -> {
                if (result) {

                    boolean response = clientsRepo
                            .DeleteUser(tb_Client.getSelectionModel().getSelectedItem().getId());
                    if (response) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("thành công");
                        alert.showAndWait();
                        clientsRepo.getListClientsFound("");
                        List<Clients> clients = GlobalDataAdmin.getInstance().getClientsFound();

                        // Chuyển đổi danh sách thông thường thành ObservableList
                        ObservableList<Clients> observableTransactionList = FXCollections
                                .observableArrayList(clients);

                        // Gán ObservableList cho ListView
                        tb_Client.setItems(observableTransactionList);

                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("thất bại");
                        alert.showAndWait();
                    }
                }
            });

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("vui lòng chọn người dùng trước khi xóa");
            alert.showAndWait();
        }
    }

    public void onClickShowData() {
        if (tb_Client.getSelectionModel().getSelectedItem() != null) {
            SavingAccountRepo savingAccountRepo = new SavingAccountRepo();
            CheckingAccountRepo checkingAccountRepo = new CheckingAccountRepo();
            checkingAccountRepo.GetCheckingAccount(tb_Client.getSelectionModel().getSelectedItem().getId());
            savingAccountRepo.GetSavingAccount(tb_Client.getSelectionModel().getSelectedItem().getId());
            DetailsDialog dialog = new DetailsDialog(GlobalData.getInstance().getSavingAccount(),
                    GlobalData.getInstance().getCheckingAccount());
            dialog.showAndWait();
        }
    }
}
