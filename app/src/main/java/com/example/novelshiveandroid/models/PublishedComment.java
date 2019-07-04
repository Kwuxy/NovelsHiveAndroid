package com.example.novelshiveandroid.models;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.Date;

public class PublishedComment {

    private Integer id;

    @SerializedName("text")
    private LinkedTreeMap text;

    private Date publication_date;
    private String username;

    public Integer getId() {
        return id;
    }

    public LinkedTreeMap getText() {
        return text;
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public String getUsername() {
        return username;
    }
}
