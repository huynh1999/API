package com.appdd.api.Model;

import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "user")
@Table(name = "user")
public class User {
    @Id
    @Column(name = "username")
    private String Username;
    @Column(name = "pass")
    private String Pass;
    @Column(name = "fullname")
    private String Fullname;
    @Column(name = "token")
    private String Token;

    public User() {
    }

    public User(String name, String password,String fullname) {
        Username = name;
        Pass = password;
        Fullname=fullname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
