package main.connect.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import main.connect.Models.Clients;
import main.java.GlobalData;

public class ClientsRepo {
    public Clients Authentic(String payee, String passWord) {
        try {

            // URL của API
            String apiUrl = "http://localhost:8080/getUserWithPayeeAndPassword?payeeaddress=" + payee + "&password="
                    + passWord;

            // Mở kết nối HTTP
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Lấy dữ liệu từ API
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Phân tích dữ liệu JSON và tạo đối tượng Clients
            String jsonResponse = response.toString();

            Clients client = Clients.parseJsonToClient(jsonResponse);

            // In thông tin của client
            System.out.println("Client ID: " + client.getId());
            System.out.println("First Name: " + client.getFirstName());
            System.out.println("Last Name: " + client.getLastName());
            System.out.println("Payee Address: " + client.getPayeeAdress());
            System.out.println("Password: " + client.getPassWord());
            System.out.println("Date of Birth: " + client.getDateOfBirth());

            // Đóng kết nối
            conn.disconnect();
            getCheckingAndSaving(client.getId());
            return client;
        } catch (IOException e) {
            // Xử lý trường hợp nhập sai tên ví hoặc mật khẩu
            e.printStackTrace();
            return null; // hoặc trả về một giá trị thích hợp khác tùy thuộc vào logic ứng dụng của bạn
        }
    }

    void getCheckingAndSaving(String id) {
        SavingAccountRepo savingAccountRepo = new SavingAccountRepo();
        CheckingAccountRepo checkingAccountRepo = new CheckingAccountRepo();
        checkingAccountRepo.GetCheckingAccount(id);
        savingAccountRepo.GetSavingAccount(id);
    }

    public String eventSavingMoney(float amount) {

        try {

            // URL của API
            String apiUrl = "http://localhost:8080/saving?id=" + GlobalData.getInstance().getClient().getId()
                    + "&amount="
                    + amount;
            // Mở kết nối HTTP
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // Lấy dữ liệu từ API
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            // Phân tích dữ liệu JSON và tạo đối tượng Clients
            String jsonResponse = response.toString();
            // Đóng kết nối
            conn.disconnect();
            System.out.println(jsonResponse);
            if (jsonResponse == "{ \"Thành công\"}") {
                System.out.println("abc");
            }
            return jsonResponse;
        } catch (IOException e) {
            // Xử lý trường hợp nhập sai tên ví hoặc mật khẩu
            e.printStackTrace();
            return "Thất bại"; // hoặc trả về một giá trị thích hợp khác tùy thuộc vào logic ứng dụng của bạn
        }
    }

    public String toJson(String id, float amount) {
        return "{"
                + "\"id\":\"" + id + "\","
                + "\"amount\":\"" + amount + "\""
                + "}";
    }
}
