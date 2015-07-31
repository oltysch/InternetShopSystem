package com.epam.ot.users;

public abstract class AbstractUser {
    private String login;
    private String email;
    private String password;
    private String name;

    public AbstractUser(String login, String email, String password, String name) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
