package main.java.Controllers.Admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import main.connect.Repository.ClientsRepo;

public class DepositController implements Initializable {

    public TextField pAddress_fld;
    public Button search_btn;
    public ListView result_listview;
    public TextField ammount_fld;
    public Button deposit_btn;
    public final ClientsRepo clientsRepo = new ClientsRepo();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        search_btn.setOnAction(e -> searchUser());
        deposit_btn.setOnAction(e -> addBalance());
    }

    public void searchUser() {
        clientsRepo.getListClientsFound(pAddress_fld.getText());
    }

    public void addBalance() {
        // clientsRepo.AddBalane(idOwner, ammount_fld) idOwner của khách hàng chọn
    }
}
