package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.Models.Model;
import main.java.Models.DatabaseDriver;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Scene scene=new Scene(loadFXML("Admin/CreateClient"));
        // stage.setScene(scene);
        // stage.show();
        Model.getInstance().getViewFactory().showLoginPage();
        //Model.getInstance().getViewFactory().showAdminWindow();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        DatabaseDriver db = new DatabaseDriver();
        launch();
    }
}