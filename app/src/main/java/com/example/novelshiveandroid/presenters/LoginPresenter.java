package com.example.novelshiveandroid.presenters;

import com.example.novelshiveandroid.models.Token;

public interface LoginPresenter {

    void performLogin(String email, String password);
    void setTokenOnGlobals(Token token);
}
