package main.connect.Repository;

import main.connect.Models.SavingAccount;
import main.connect.Models.Transaction;
import main.java.GlobalData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class TransactionRepo {
    public void GetListTransaction(String id) {
        try {
            // URL của API
            String apiUrl = "http://localhost:8080/transaction/" + id;
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
            List<Transaction> transactions = Transaction.parseJsonToTransactionList(jsonResponse);
            // Xử lý danh sách transactions (ví dụ: in ra hoặc lưu vào đâu đó)
            GlobalData.getInstance().setTransaction(transactions);
            // Đóng kết nối
            conn.disconnect();
        } catch (IOException e) {
            // Xử lý trường hợp nhập sai tên ví hoặc mật khẩu
            e.printStackTrace();
        }
    }

    public String addTransation(String idSender, String idReceiver, float amount, String Date, String message) {
        String apiUrl = "http://localhost:8080/transaction/";
        String jsonInputString = toJson(idSender, idReceiver, amount, Date, message);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(apiUrl);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(jsonInputString));

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int responseCode = response.getCode();
                String responseBody = EntityUtils.toString(response.getEntity());
                return responseBody;
                // Cập nhật nhãn trong luồng giao diện người dùng

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public String toJson(String idSender, String idReceiver, float amount, String Date, String message) {
        return "{"
                + "\"addressSender\":\"" + idSender + "\","
                + "\"addressReceiver\":\"" + idReceiver + "\","
                + "\"amount\":" + amount + ","
                + "\"date\":\"" + Date + "\","
                + "\"message\":\"" + message + "\""
                + "}";
    }
}
