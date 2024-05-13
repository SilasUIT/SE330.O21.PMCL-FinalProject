module main.java {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome;
    requires org.mongodb.driver.sync.client;    
    
    
    
    opens main.java to javafx.fxml;
    exports main.java;
    exports main.java.Controllers;
    exports main.java.Controllers.Admin;
    exports main.java.Controllers.Client;
    exports main.java.Models;
    exports main.java.Views;
}
