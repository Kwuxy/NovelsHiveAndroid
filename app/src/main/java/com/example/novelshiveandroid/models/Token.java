package com.example.novelshiveandroid.models;

import java.util.Date;

public class Token {

    private String id;
    private int ttl;
    private Date created;
    private int userId;

    public String getId() {
        return id;
    }

    public int getTtl() {
        return ttl;
    }

    public Date getCreated() {
        return created;
    }

    public int getUserId() {
        return userId;
    }
}
