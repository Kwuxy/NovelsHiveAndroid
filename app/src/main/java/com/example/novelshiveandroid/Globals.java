package com.example.novelshiveandroid;

import android.app.Application;

import com.example.novelshiveandroid.models.Token;

public class Globals extends Application {

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
