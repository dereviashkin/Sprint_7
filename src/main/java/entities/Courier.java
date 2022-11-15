package entities;

public class Courier {
    private final String login;
    private final String password;
    private final String name;

    public Courier(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }
}
