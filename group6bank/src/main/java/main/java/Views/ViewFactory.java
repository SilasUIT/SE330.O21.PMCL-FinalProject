package main.java.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.Controllers.Client.ClientController;

public class ViewFactory {
    private AnchorPane dashboardView;

    public ViewFactory(){

    }

    public AnchorPane getDashboardView()
    {
        if(dashboardView==null){
            try{
                dashboardView=new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml")).load();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return dashboardView;
    }
    public void showLoginPage()
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    public void showClientWindow()
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController=new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }


    private void createStage(FXMLLoader loader)
    {
        Scene scene=null;
        try{
            scene=new Scene(loader.load());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("group6bank");
        stage.show();
    }
}
