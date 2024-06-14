package main.java.Views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.Controllers.Admin.AdminController;
import main.java.Controllers.Client.ClientController;

public class ViewFactory {
    private AccountType loginAccoutnType;
    // Client views
    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane transactionsView;
    private AnchorPane accountsView;

    // Admin views
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane adminCreateClientView;
    private AnchorPane adminClientsView;
    private AnchorPane adminDepositView;

    public ViewFactory() {
        this.loginAccoutnType = AccountType.CLIENT;
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public AccountType getLoginAccoutnType() {
        return loginAccoutnType;
    }

    public void setLoginAccoutnType(AccountType loginAccoutnType) {
        this.loginAccoutnType = loginAccoutnType;
    }

    public ObjectProperty<ClientMenuOptions> getSelectedMenuItems() {
        return clientSelectedMenuItem;
    }

    public ObjectProperty<AdminMenuOptions> getSelectedAdminMenuItems() {

        return adminSelectedMenuItem;
    }
    /* Client views Section */

    public AnchorPane getDashboardView() {

        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getTransactionsView() {
        if (transactionsView == null) {
            try {
                transactionsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Transactions.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return transactionsView;
    }

    public AnchorPane getAccountsView() {
        if (accountsView == null) {
            try {
                accountsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Accounts.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return accountsView;
    }

    /* Admin views Section */

    public AnchorPane getadminCreateClientView() {
        if (adminCreateClientView == null) {
            try {
                adminCreateClientView = new FXMLLoader(getClass().getResource("/Fxml/Admin/CreateClient.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return adminCreateClientView;
    }

    public AnchorPane getadminClientsView() {
        if (adminClientsView == null) {
            try {
                adminClientsView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Clients.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return adminClientsView;
    }

    public AnchorPane getadminDepositView() {
        if (adminDepositView == null) {
            try {
                adminDepositView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Deposit.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return adminDepositView;
    }

    public void showLoginPage() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    public void showClientWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }

    public void showAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
        createStage(loader);
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/logo.png"))));
        stage.setResizable(false);
        stage.setTitle("group6bank");
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }

    public void clearCachedViews() {
        dashboardView = null;
        transactionsView = null;
        accountsView = null;
    }
}
