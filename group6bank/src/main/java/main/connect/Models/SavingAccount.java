package main.connect.Models;

public class SavingAccount {
    String id;
    String idOwner;
    String accountNumber;
    float withDrawLimit;
    float balance;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOwner() {
        return this.idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getWithDrawLimit() {
        return this.withDrawLimit;
    }

    public void setWithDrawLimit(float withDrawLimit) {
        this.withDrawLimit = withDrawLimit;
    }

    public float getBalance() {
        return this.balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public SavingAccount(String id, String idOwner, String accountNumber, float withDrawLimit, float balance) {
        this.id = id;
        this.idOwner = idOwner;
        this.accountNumber = accountNumber;
        this.withDrawLimit = withDrawLimit;
        this.balance = balance;
    }

    public static SavingAccount parseJsonToSavingAccount(String jsonResponse) {
        String id = "";
        String idOwner = "";
        String accountNumber = "";
        float withDrawLimit = 0;
        float balance = 0;

        String[] parts = jsonResponse.replaceAll("[{}\"]", "").split(",");
        for (String part : parts) {
            String[] keyValue = part.split(":");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            switch (key) {
                case "id":
                    id = value;
                    break;
                case "idOwner":
                    idOwner = value;
                    break;
                case "accountNumber":
                    accountNumber = value;
                    break;
                case "withDrawLimit":
                    withDrawLimit = Float.parseFloat(value);
                    break;
                case "balance":
                    balance = Float.parseFloat(value);
                    break;
                default:
                    // Xử lý trường hợp khác nếu cần thiết
                    break;
            }
        }

        // Tạo đối tượng CheckingAccount từ dữ liệu phân tích
        return new SavingAccount(id, idOwner, accountNumber, withDrawLimit, balance);
    }

}
