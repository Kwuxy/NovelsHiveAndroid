package com.example.novelshiveandroid.viewModels;

import android.text.TextUtils;

import com.example.novelshiveandroid.Globals;
import com.example.novelshiveandroid.models.Token;
import com.example.novelshiveandroid.presenters.LoginPresenter;
import com.example.novelshiveandroid.views.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.APIClient.jsonPlaceHolderApi;

public class LoginViewModel implements LoginPresenter {

    LoginView mLoginView;

    public LoginViewModel(LoginView loginView){
        this.mLoginView = loginView;
    }

    @Override
    public void performLogin(String email, String password) {

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            mLoginView.loginValidations();
            return;
        }

        Call<Token> call = jsonPlaceHolderApi.loginUser(email, password);

        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if(!response.isSuccessful()){
                    mLoginView.loginUnsuccessful();
                    return;
                }
                setTokenOnGlobals(response.body());
                mLoginView.loginSuccess();
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                System.out.print(t.getMessage());
                mLoginView.loginFailure();
            }
        });

    }

    @Override
    public void setTokenOnGlobals(Token token) {
        Globals.getInstance().setCurrentToken(token);
    }
}
