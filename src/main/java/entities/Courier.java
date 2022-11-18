package entities;

public class Courier {
    private String login;
    private String password;
    private String name;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Courier(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public Courier(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
