package dambat.models;

public class Login {

    private String username;
    private String password;
    

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Login{");
        sb.append("username=").append(username);
        sb.append(", password=").append(password);
        sb.append('}');
        return sb.toString();
    }
}
