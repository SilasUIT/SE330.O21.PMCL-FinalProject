package main.connect.Models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Transaction {
    String id;

    String addressSender;
    String addressReceiver;
    float amount;
    String date;
    String message;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddressSender() {
        return this.addressSender;
    }

    public void setAddressSender(String addressSender) {
        this.addressSender = addressSender;
    }

    public String getAddressReceiver() {
        return this.addressReceiver;
    }

    public void setAddressReceiver(String addressReceiver) {
        this.addressReceiver = addressReceiver;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Transaction(String id, String addressSender, String addressReceiver, float amount, String date,
            String message) {
        this.id = id;
        this.addressSender = addressSender;
        this.addressReceiver = addressReceiver;
        this.amount = amount;
        this.date = date;
        this.message = message;
    }

    public static Transaction parseJsonToTransaction(String jsonResponse) {
        String id = "";
        String addressSender = "";
        String addressReceiver = "";
        float amount = 0;
        String date = "";
        String message = "";

        String[] parts = jsonResponse.replaceAll("[{}\"]", "").split(",");
        for (String part : parts) {
            String[] keyValue = part.split(":");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            switch (key) {
                case "id":
                    id = value;
                    break;
                case "addressSender":
                    addressSender = value;
                    break;
                case "addressReceiver":
                    addressReceiver = value;
                    break;
                case "amount":
                    amount = Float.parseFloat(value);
                    break;
                case "date":
                    date = value;
                    break;
                case "message":
                    message = value;
                    break;
                default:
                    // Xử lý trường hợp khác nếu cần thiết
                    break;
            }
        }

        // Tạo đối tượng CheckingAccount từ dữ liệu phân tích
        return new Transaction(id, addressSender, addressReceiver, amount, date, message);
    }

    public static List<Transaction> parseJsonToTransactionList(String jsonResponse) {
        List<Transaction> transactions = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(jsonResponse);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String id = jsonObject.getString("id");
            String addressSender = jsonObject.getString("addressSender");
            String addressReceiver = jsonObject.getString("addressReceiver");
            float amount = jsonObject.getFloat("amount");
            String date = jsonObject.getString("date");
            String message = jsonObject.getString("message");

            Transaction transaction = new Transaction(id, addressSender, addressReceiver, amount, date, message);
            transactions.add(transaction);
        }

        return transactions;
    }

    public String toJson() {
        return "{"
                + "\"addressSender\":\"" + addressSender + "\","
                + "\"addressReceiver\":\"" + addressReceiver + "\","
                + "\"amount\":" + amount + ","
                + "\"date\":\"" + date + "\","
                + "\"message\":\"" + message + "\""
                + "}";
    }
}
