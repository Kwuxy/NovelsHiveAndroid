package com.example.novelshiveandroid;

import android.app.Application;

import com.example.novelshiveandroid.models.Token;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Globals extends Application {

    public static final String KEY_STORY_ID = "story_id";
    public static final String KEY_CHAPTER_ID = "chapter_id";
    public static final String KEY_PREVIOUS_CHAPTER_ID = "previous_chapter_id";
    public static final String KEY_NEXT_CHAPTER_ID = "next_chapter_id";

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy MMMM dd", Locale.ENGLISH);

    private static Globals instance;

    private static Token currentToken;

    public Globals(){}

    public static Token getCurrentToken() {
        return currentToken;
    }

    public static void setCurrentToken(Token token) {
        currentToken = token;
    }

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance = new Globals();
        }
        return instance;
    }

    public static String convertToText(ArrayList<Double> doubleData) {
        byte[] data = new byte[doubleData.size()];
        for(int i = 0; i < doubleData.size(); i++)
            data[i] = doubleData.get(i).byteValue();
        return new String(data);
    }
}
