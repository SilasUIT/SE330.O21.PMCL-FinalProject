package main.java.Controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.java.Models.Model;

public class LogoutConfirmationController {
    @FXML
    private void handleLogoutConfirm(ActionEvent event) {
        List<Window> openWindows = new ArrayList<>(Window.getWindows());
        for (Window window : openWindows) {
            if (window instanceof Stage) {
                ((Stage) window).close();
            }
        }
        try {
            Model.getInstance().getViewFactory().showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
