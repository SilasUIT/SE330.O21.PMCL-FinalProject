package main.connect.Models;

public class Clients {
    private String id;
    private String firstName;
    private String lastName;
    private String payeeAdress;
    private String passWord;
    private String dateOfBirth;

    // Thêm constructor public không tham số
    public Clients() {
    }

    public Clients(String id, String firstName, String lastName, String payeeAdress, String passWord,
            String dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.payeeAdress = payeeAdress;
        this.passWord = passWord;
        this.dateOfBirth = dateOfBirth;
    }

    // Thêm các getter và setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPayeeAdress() {
        return payeeAdress;
    }

    public void setPayeeAdress(String payeeAdress) {
        this.payeeAdress = payeeAdress;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public static Clients parseJsonToClient(String jsonResponse) {
        String id = "";
        String firstName = "";
        String lastName = "";
        String payeeAdress = "";
        String passWord = "";
        String dateOfBirth = "";

        String[] parts = jsonResponse.replaceAll("[{}\"]", "").split(",");
        for (String part : parts) {
            String[] keyValue = part.split(":");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            switch (key) {
                case "id":
                    id = value;
                    break;
                case "firstName":
                    firstName = value;
                    break;
                case "lastName":
                    lastName = value;
                    break;
                case "payeeAdress":
                    payeeAdress = value;
                    break;
                case "passWord":
                    passWord = value;
                    break;
                case "dateOfBirth":
                    dateOfBirth = value;
                    break;
                default:
                    // Xử lý trường hợp khác nếu cần thiết
                    break;
            }
        }

        // Tạo đối tượng Clients từ dữ liệu phân tích
        return new Clients(id, firstName, lastName, payeeAdress, passWord, dateOfBirth);
    }

}
