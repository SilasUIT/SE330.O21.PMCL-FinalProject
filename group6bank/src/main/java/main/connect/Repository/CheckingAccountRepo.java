package main.connect.Repository;

import main.connect.Models.CheckingAccount;
import main.java.GlobalData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CheckingAccountRepo {
    public void GetCheckingAccount(String idOwner) {
        try {

            // URL của API
            String apiUrl = "http://localhost:8080/checking_account/" + idOwner;

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

            CheckingAccount checkingAccount = CheckingAccount.parseJsonToCheckingAccount(jsonResponse);
            // Đóng kết nối
            conn.disconnect();
            GlobalData.getInstance().setCheckingAccount(null);
            GlobalData.getInstance().setCheckingAccount(checkingAccount);
        } catch (IOException e) {
            // Xử lý trường hợp nhập sai tên ví hoặc mật khẩu
            e.printStackTrace();

        }
    }
}
