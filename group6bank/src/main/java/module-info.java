module main.java {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires de.jensd.fx.glyphs.fontawesome;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;

    requires org.json;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires org.apache.httpcomponents.core5.httpcore5;

    opens main.java to javafx.fxml;

    exports main.java;
    exports main.java.Controllers;
    exports main.java.Controllers.Admin;
    exports main.java.Controllers.Client;
    exports main.java.Models;
    exports main.java.Views;
    exports main.connect.Models;
}
