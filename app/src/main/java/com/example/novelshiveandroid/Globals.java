package com.example.novelshiveandroid;

import android.app.Application;

import com.example.novelshiveandroid.models.Token;

public class Globals extends Application {

    public static final String KEY_STORY_ID = "story_id";
    public static final String KEY_CHAPTER_ID = "chapter_id";
    public static final String KEY_PREVIOUS_CHAPTER_ID = "previous_chapter_id";
    public static final String KEY_NEXT_CHAPTER_ID = "next_chapter_id";

    private static Globals instance;

    private Token currentToken;

    public Globals(){}

    public Token getCurrentToken() {
        return currentToken;
    }

    public void setCurrentToken(Token currentToken) {
        this.currentToken = currentToken;
    }

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance = new Globals();
        }
        return instance;
    }
}
