package com.itheima.web;

public class User {
    private String username;
    private String passwoed;

    public User() {
    }

    public User(String username, String passwoed) {
        this.username = username;
        this.passwoed = passwoed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswoed() {
        return passwoed;
    }

    public void setPasswoed(String passwoed) {
        this.passwoed = passwoed;
    }
}
