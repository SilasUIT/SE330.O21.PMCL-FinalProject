package main.java.Views;

public enum AccountType {
    CLIENT {
        @Override
        public String toString() {
            return "Khách hàng";
        }
    },
    ADMIN {
        @Override
        public String toString() {
            return "Quản trị viên";
        }
    };
    public static AccountType fromString(String text) {
        for (AccountType type : AccountType.values()) {
            if (type.toString().equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }
}

