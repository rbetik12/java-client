package io.rbetik12.models;

public class AuthWindowTranslation {
    public String username;
    public String password;
    public String submit;
    public String close;
    public String error;

    @Override
    public String toString() {
        return "AuthWindowTranslation{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", submit='" + submit + '\'' +
                ", close='" + close + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
