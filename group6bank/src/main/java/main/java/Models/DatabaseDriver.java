package main.java.Models;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import io.github.cdimascio.dotenv.Dotenv;


public class DatabaseDriver {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public DatabaseDriver() {
        try {
            // Thay YOUR_CONNECTION_STRING bằng chuỗi kết nối MongoDB Cloud của bạn
            Dotenv dotenv = Dotenv.configure()
                    .directory("group6bank\\.env")
                    .load();
            String mongoUrl = dotenv.get("MONGO_URL");
            mongoClient = MongoClients.create(mongoUrl);
            // Thay YOUR_DATABASE_NAME bằng tên database bạn muốn kết nối
            database = mongoClient.getDatabase("group6bank");
            System.out.println("Connected to MongoDB!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
