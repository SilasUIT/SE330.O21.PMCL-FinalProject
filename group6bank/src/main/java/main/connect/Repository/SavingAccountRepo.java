package main.connect.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import main.connect.Models.SavingAccount;
import main.java.GlobalData;

public class SavingAccountRepo {
    public void GetSavingAccount(String idOwner) {
        try {

            // URL của API
            String apiUrl = "http://localhost:8080/saving_account/" + idOwner;

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

            SavingAccount savingAccount = SavingAccount.parseJsonToSavingAccount(jsonResponse);
            System.out.println(savingAccount.getId());
            System.out.println(savingAccount.getAccountNumber());
            System.out.println(savingAccount.getBalance());
            System.out.println(savingAccount.getWithDrawLimit());
            // Đóng kết nối
            conn.disconnect();
            GlobalData.getInstance().setSavingAccount(savingAccount);

        } catch (IOException e) {
            // Xử lý trường hợp nhập sai tên ví hoặc mật khẩu
            e.printStackTrace();

        }
    }
}
