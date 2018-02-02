package kitaluna.foodorder.models.login;

/**
 * Created by Van Hien on 12/31/2017.
 */

public class AccountLogin {
    private String email;
    private String password;

    public AccountLogin() {
    }

    public AccountLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
