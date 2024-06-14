package main.java.Controllers;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class DeleteUserDialog extends Dialog<Boolean> {

    public DeleteUserDialog(String userName) {
        setTitle("Xác nhận xóa");
        setHeaderText("Bạn có chắc chắn muốn xóa người dùng này không?");

        // Set the button types.
        ButtonType deleteButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(deleteButtonType, ButtonType.CANCEL);

        // Create the content.
        VBox content = new VBox();
        content.setAlignment(Pos.CENTER_LEFT);
        content.setSpacing(10);
        content.setPadding(new Insets(20, 150, 10, 10));
        content.getChildren().add(new Label("Người dùng: " + userName));

        getDialogPane().setContent(content);

        // Convert the result to a Boolean when the OK button is clicked.
        setResultConverter(dialogButton -> {
            if (dialogButton == deleteButtonType) {
                return true;
            }
            return false;
        });
    }
}
