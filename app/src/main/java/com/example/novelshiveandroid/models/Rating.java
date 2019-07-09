package com.example.novelshiveandroid.models;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

public class Rating {

    private int id;
    private String symbol;
    @SerializedName("description")
    private LinkedTreeMap description;

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public LinkedTreeMap getDescription() {
        return description;
    }
}
