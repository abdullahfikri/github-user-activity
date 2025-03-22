package dev.mfikri.entity;

public class Actor {
    private int id;
    private String login;
    private String display_login;

    public Actor() {
    }

    public Actor(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDisplay_login() {
        return display_login;
    }

    public void setDisplay_login(String display_login) {
        this.display_login = display_login;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
