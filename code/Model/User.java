package id.co.hananda.atry.Model;

/**
 * Created by user on 1/24/2018.
 */

public class User {
    private String userName;
    private String password;

    public User(){

    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
