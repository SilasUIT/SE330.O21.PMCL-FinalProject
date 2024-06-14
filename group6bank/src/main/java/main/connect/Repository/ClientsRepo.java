package main.connect.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import main.connect.Models.Clients;
import main.connect.Models.Transaction;
import main.java.GlobalData;
import main.java.GlobalDataAdmin;

public class ClientsRepo {
    public boolean Authentic(String payee, String passWord) {
        try {

            // URL của API
            String apiUrl = "http://localhost:8080/getUserWithPayeeAndPassword?payeeaddress=" + payee + "&password="
                    + passWord;

            // Mở kết nối HTTP
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Lấy dữ liệu từ API
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) { // 200 OK
                // Lấy dữ liệu từ API
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Phân tích dữ liệu JSON và tạo đối tượng Clients
                String jsonResponse = response.toString();
                Clients client = Clients.parseJsonToClient(jsonResponse);

                // Đóng kết nối
                conn.disconnect();
                GlobalData.getInstance().setClient(client);
                getEverything(client);
                return true;
            } else {

                conn.disconnect();
                return false; // hoặc trả về một giá trị thích hợp khác tùy thuộc vào logic ứng dụng của bạn
            }
        } catch (IOException e) {
            // Xử lý trường hợp nhập sai tên ví hoặc mật khẩu
            e.printStackTrace();
            return false; // hoặc trả về một giá trị thích hợp khác tùy thuộc vào logic ứng dụng của bạn
        }
    }

    void getCheckingSavingAndTransaction(Clients clients) {
        SavingAccountRepo savingAccountRepo = new SavingAccountRepo();
        CheckingAccountRepo checkingAccountRepo = new CheckingAccountRepo();
        TransactionRepo transactionRepo = new TransactionRepo();
        checkingAccountRepo.GetCheckingAccount(clients.getId());
        savingAccountRepo.GetSavingAccount(clients.getId());
        transactionRepo.GetListTransaction(clients.getPayeeAdress());
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
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
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

            if (jsonResponse == "{ \"Thành công\"}") {

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

    public boolean AuthenticWithAdmin(String userName, String passWord) {
        try {

            // URL của API
            String apiUrl = "http://localhost:8080/admin?userName=" + userName + "&passWord=" + passWord;

            // Mở kết nối HTTP
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Lấy dữ liệu từ API
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) { // 200 OK
                // Lấy dữ liệu từ API
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Phân tích dữ liệu JSON và tạo đối tượng Clients
                String jsonResponse = response.toString();
                if (jsonResponse.equals("\"NOT_FOUND\"")) {

                    return false;
                }

                // Đóng kết nối
                conn.disconnect();
                return true;
            } else {

                conn.disconnect();
                return false; // hoặc trả về một giá trị thích hợp khác tùy thuộc vào logic ứng dụng của bạn
            }
        } catch (IOException e) {
            // Xử lý trường hợp nhập sai tên ví hoặc mật khẩu
            e.printStackTrace();
            return false; // hoặc trả về một giá trị thích hợp khác tùy thuộc vào logic ứng dụng của bạn
        }
    }

    public void getEverything(Clients clients) {
        GlobalData.getInstance().clearAllDataExeptClient();
        getCheckingSavingAndTransaction(clients);
    }

    public String toJson(String fName, String lName, String payeeAdress, String passWord) {
        return "{"
                + "\"firstName\":\"" + fName + "\","
                + "\"lastName\":\"" + lName + "\","
                + "\"payeeAddress\":\"" + payeeAdress + "\","
                + "\"passWord\":\"" + passWord + "\","
                + "\"dateOfBirth\":\"29/01/2003 \""
                + "}";
    }

    public String addUser(String fName, String lName, String payeeAdress, String passWord, float moneyCheck,
            float moneySave) {
        String apiUrl = "http://localhost:8080/addUserwithMoney?moneyCh=" + moneyCheck + "&moneySv=" + moneySave;
        String jsonInputString = toJson(fName, lName, payeeAdress, passWord);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(apiUrl);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(jsonInputString));

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int responseCode = response.getCode();
                String responseBody = EntityUtils.toString(response.getEntity());

                if (responseCode == 200) {
                    return "Thêm Thành công";
                }
                return responseBody;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Thất bại";
        }
    }

    public void getListClients() {
        try {
            // URL của API
            String apiUrl = "http://localhost:8080/getAllUser";
            // Mở kết nối HTTP
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Kiểm tra mã phản hồi
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            // Lấy dữ liệu từ API
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Phân tích dữ liệu JSON và tạo danh sách đối tượng Clients
            String jsonResponse = response.toString();
            List<Clients> clients = Clients.parseJsonToClientList(jsonResponse);

            // In ra danh sách Clients để kiểm tra
            for (Clients client : clients) {
                System.out.println(
                        "ID: " + client.getId() + ", Name: " + client.getFirstName() + " " + client.getLastName());
            }
            GlobalDataAdmin.getInstance().setClients(null);
            GlobalDataAdmin.getInstance().setClients(clients);
            // Đóng kết nối
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getListClientsFound(String payeeAddress) {
        try {
            // URL của API
            String apiUrl = "http://localhost:8080/User?payeeAddress=" + payeeAddress;
            // Mở kết nối HTTP
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Kiểm tra mã phản hồi
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            // Lấy dữ liệu từ API
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Phân tích dữ liệu JSON và tạo danh sách đối tượng Clients
            String jsonResponse = response.toString();
            List<Clients> clients = Clients.parseJsonToClientList(jsonResponse);

            // In ra danh sách Clients để kiểm tra
            GlobalDataAdmin.getInstance().setClientsFound(null);
            GlobalDataAdmin.getInstance().setClientsFound(clients);
            // Đóng kết nối
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String AddBalane(String IdOwner, float amount) {
        try {

            // URL của API
            String apiUrl = "http://localhost:8080/checking_account/addBalance?IdOwner=" + IdOwner + "&amount="
                    + amount;

            // Mở kết nối HTTP
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Lấy dữ liệu từ API
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) { // 200 OK
                // Lấy dữ liệu từ API
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Đóng kết nối
                conn.disconnect();
                getListClients();
                return "Thành công";
            } else {
                conn.disconnect();
                return "Thất bại"; // hoặc trả về một giá trị thích hợp khác tùy thuộc vào logic ứng dụng của bạn
            }
        } catch (IOException e) {
            // Xử lý trường hợp nhập sai tên ví hoặc mật khẩu
            e.printStackTrace();
            return "Thất bại"; // hoặc trả về một giá trị thích hợp khác tùy thuộc vào logic ứng dụng của bạn
        }
    }

    public boolean DeleteUser(String idUser) {
        try {
            // URL của API
            String apiUrl = "http://localhost:8080/deleteUser/" + idUser;

            // Mở kết nối HTTP
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            System.out.println("1");
            // Lấy dữ liệu từ API
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) { // 200 OK
                System.out.println("2");
                // Lấy dữ liệu từ API
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Đóng kết nối
                conn.disconnect();

                return true;
            } else {
                conn.disconnect();
                return false;
            }
        } catch (IOException e) {
            System.out.println("3");
            e.printStackTrace();
            return false;
        }
    }
}
