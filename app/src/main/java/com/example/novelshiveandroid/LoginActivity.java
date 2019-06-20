package com.example.novelshiveandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class LoginActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Mode night, easy dark theme
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle(R.string.title_login_activity);

        this.setupSharedPreferences();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    public void goToHome(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        /*
        if (key.equals(getString(R.string.pref_dark_theme_bool_key))) {

            if (sharedPreferences.getBoolean(getString(R.string.pref_dark_theme_bool_key), false)) {
                //  Night theme enabled

                this.setTheme(R.style.AppTheme_DarkTheme);
                this.getApplication().setTheme(R.style.AppTheme_DarkTheme);
                //darkTheme = true;

            } else {
                this.setTheme(R.style.AppTheme_LightTheme);
                this.getApplication().setTheme(R.style.AppTheme_LightTheme);
                //darkTheme = false;
            }

            this.recreate(); // This is important. It allows the theme change to take effect.
        }
        */
    }
}