package server.beans;

/**
 * Created by Johan on 2016-03-04.
 */
public class Login {

    private String loginId, password;
    private boolean getTests = false;

    public Login (){

    }

    public Login (String loginId, String password){
        this.loginId = loginId;
        this.password = password;
    }

    public Login (String loginId, String password, boolean getTests){
        this.loginId = loginId;
        this.password = password;
        this.getTests = getTests;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
