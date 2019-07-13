package com.example.novelshiveandroid.models;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Date;

public class Comment {

    private Integer id;

    @SerializedName("text")
    private LinkedTreeMap text;

    private int userId;
    private int storyChapterId;
    private Date publication_date;

    public Comment(String text, int userId, int storyChapterId) {
        this.text = convertStringToLinkedTreeMap(text);
        this.userId = userId;
        this.storyChapterId = storyChapterId;
        this.publication_date = new Date();
    }

    public Integer getId() {
        return id;
    }

    public LinkedTreeMap getText() {
        return text;
    }

    public int getUserId() {
        return userId;
    }

    public int getStoryChapterId() {
        return storyChapterId;
    }

    private LinkedTreeMap convertStringToLinkedTreeMap(String text){
        LinkedTreeMap linkedTreeMap = new LinkedTreeMap();

        byte[] data = text.getBytes();
        int times = Double.SIZE / Byte.SIZE;
        ArrayList<Double> doubles = new ArrayList<>();
        for(int i=0;i<data.length;i++){
            double d = data[i];
            doubles.add(d);
        }
        linkedTreeMap.put("type", "Buffer");
        linkedTreeMap.put("data", doubles);

        return linkedTreeMap;
    }
}
