package com.example.novelshiveandroid.viewModels;

import com.example.novelshiveandroid.models.User;
import com.example.novelshiveandroid.presenters.SettingsPresenter;
import com.example.novelshiveandroid.views.SettingsView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.APIClient.jsonPlaceHolderApi;

public class SettingsViewModel implements SettingsPresenter {

    SettingsView mSettingsView;
    User currentUser;

    public SettingsViewModel(SettingsView mSettingsView) {
        this.mSettingsView = mSettingsView;
    }

    @Override
    public void getUserSettings(int userId) {
        Call<User> call = jsonPlaceHolderApi.getUserInfos(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                currentUser = response.body();
                mSettingsView.displayCurrentSettings(currentUser.getFont_size(),
                        currentUser.getFont_family(), currentUser.getTheme());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }

    @Override
    public void changeSettings(User user, Integer font_size, String font_family, String realm) {
        Call<User> call = jsonPlaceHolderApi.updateUser(user.getId(), user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                currentUser = response.body();
                mSettingsView.changeSettingsSuccess();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }
}
