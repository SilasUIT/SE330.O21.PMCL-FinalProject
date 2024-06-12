module main.java {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires de.jensd.fx.glyphs.fontawesome;  
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;

    opens main.java to javafx.fxml;
    exports main.java;
    exports main.java.Controllers;
    exports main.java.Controllers.Admin;
    exports main.java.Controllers.Client;
    exports main.java.Models;
    exports main.java.Views;
}
