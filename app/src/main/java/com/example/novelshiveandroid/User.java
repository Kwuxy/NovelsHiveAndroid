package com.example.novelshiveandroid;

import com.google.gson.annotations.SerializedName;

public class User {

    private int id;
    private String email;
    private String admin;
    private int font_size;
    private String font_family;
    private String theme;
    private String realm;
    private String username;

    @SerializedName("body")
    private String description;

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAdmin() {
        return admin;
    }

    public int getFont_size() {
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

    public String getDescription() {
        return description;
    }
}
