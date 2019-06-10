package com.example.novelshiveandroid.models;

import com.google.gson.annotations.SerializedName;

public class User {

    private Integer id;

    @SerializedName("body")
    private String description;

    private String password;
    private String email;
    private Boolean admin;
    private Integer font_size;
    private String font_family;
    private String theme;
    private String realm;
    private String username;
    private Boolean emailVerified;
    private String verificationToken;

    //Constructor With All User Infos
    public User(String description, String password, String email, Integer font_size,
                String font_family, String theme, String realm, String username) {
        this.description = description;
        this.password = password;
        this.email = email;
        this.admin = false;
        this.font_size = font_size;
        this.font_family = font_family;
        this.theme = theme;
        this.realm = realm;
        this.username = username;
        this.emailVerified = false;
        this.verificationToken = null;
    }

    //Constructor To Register
    public User(String email, String password, String username){
        this.description = null;
        this.password = password;
        this.email = email;
        this.admin = false;
        this.font_size = 11;
        this.font_family = "Arial";
        this.theme = "light";
        this.realm = null;
        this.username = username;
        this.emailVerified = false;
        this.verificationToken = null;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public Integer getFont_size() {
        return font_size;
    }

    public String getFont_family() {
        return font_family;
    }

    public String getTheme() {
        return theme;
    }

    public String getRealm() {
        return realm;
    }

    public String getUsername() {
        return username;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

}
