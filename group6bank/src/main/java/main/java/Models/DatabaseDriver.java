package main.java.Models;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DatabaseDriver {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public DatabaseDriver() {
        try {
            // Thay YOUR_CONNECTION_STRING bằng chuỗi kết nối MongoDB Cloud của bạn
            String connectionString = "mongodb+srv://admin:notisadmin@cluster0.k03oif2.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
            mongoClient = MongoClients.create(connectionString);
            // Thay YOUR_DATABASE_NAME bằng tên database bạn muốn kết nối
            database = mongoClient.getDatabase("group6bank");
            System.out.println("Connected to MongoDB!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
