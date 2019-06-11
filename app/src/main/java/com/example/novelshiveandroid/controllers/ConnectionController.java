package com.example.novelshiveandroid.controllers;

import android.content.Context;
import android.widget.Toast;

import com.example.novelshiveandroid.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.APIClient.jsonPlaceHolderApi;

public abstract class ConnectionController {

    public static void registerUser(final Context context, String email, String password, String username){
        User user = new User(email, password, username);
        Call<User> call = jsonPlaceHolderApi.registerUser(user);

        call.enqueue(new Callback<User>() {
            String message;

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    message = "Rip : " + response.message();
                }
                else message = "Merci de vous être inscrit " + response.body().getUsername() +
                        ". Veuillez vérifier votre compte via l'email de confirmation sur votre " +
                        "boite mail " + response.body().getEmail() + ".";
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                message = "Une erreur est survenue.";
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
