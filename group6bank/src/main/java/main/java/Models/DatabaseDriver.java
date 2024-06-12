package main.java.Models;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import io.github.cdimascio.dotenv.Dotenv;


public class DatabaseDriver {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public DatabaseDriver() {
        try {
            // Thay YOUR_CONNECTION_STRING bằng chuỗi kết nối MongoDB Cloud của bạn
            Dotenv dotenv = Dotenv.configure()
                    .directory("./")
                    .load();

            mongoClient = MongoClients.create(dotenv.get("MONGO_URL"));
            // Thay YOUR_DATABASE_NAME bằng tên database bạn muốn kết nối
            database = mongoClient.getDatabase(dotenv.get("MONGODB_NAME"));
            System.out.println("Connected to MongoDB!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Client section
     */
    public boolean authenticateUser(String payeeAddress, String password) {
        // Lấy collection chứa thông tin người dùng
        MongoCollection<Document> users = database.getCollection("Clients");
    
        // Truy vấn để tìm người dùng dựa trên PayeeAddress và password
        Document user = users.find(Filters.and(Filters.eq("PayeeAddress", payeeAddress), Filters.eq("Password", password))).first();
    
        // Kiểm tra xem có tìm thấy người dùng không
        return user != null;
    }

    /*
     * Admin section
     */

    /*
     * Utility Methods
     */
}
