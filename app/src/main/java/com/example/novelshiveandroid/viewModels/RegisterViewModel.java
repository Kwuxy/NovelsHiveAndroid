package com.example.novelshiveandroid.viewModels;

import android.text.TextUtils;

import com.example.novelshiveandroid.models.User;
import com.example.novelshiveandroid.presenters.RegisterPresenter;
import com.example.novelshiveandroid.views.RegisterView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.APIClient.jsonPlaceHolderApi;

public class RegisterViewModel implements RegisterPresenter {

    RegisterView mRegisterView;

    public RegisterViewModel(RegisterView mRegisterView) {
        this.mRegisterView = mRegisterView;
    }

    @Override
    public void performRegister(String email, String password, String checkPassword, String username) {
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(checkPassword) || TextUtils.isEmpty(username)
                || !(password.equals(checkPassword))) {
            mRegisterView.registerValidations();
            return;
        }

        User user = new User(email, password, username);
        Call<User> call = jsonPlaceHolderApi.registerUser(user);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    mRegisterView.registerUnsuccessful();
                    return;
                }
                mRegisterView.registerSuccess();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mRegisterView.registerFailure();
            }
        });

    }
}
