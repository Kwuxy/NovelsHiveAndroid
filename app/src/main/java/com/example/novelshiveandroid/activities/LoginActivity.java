package com.example.novelshiveandroid.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.presenters.LoginPresenter;
import com.example.novelshiveandroid.viewModels.LoginViewModel;
import com.example.novelshiveandroid.views.LoginView;

public class LoginActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener, LoginView {

    private EditText et_email;
    private EditText et_password;
    private String email;
    private String password;

    LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Mode night, easy dark theme
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle(R.string.title_login_activity);

        this.setupSharedPreferences();

        mLoginPresenter = new LoginViewModel(LoginActivity.this);

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

    public void signIn(View view) {
        attemptLogin();
    }

    public void attemptLogin() {
        boolean mCancel = this.loginValidation();
        if (mCancel) {

        }
        else {
            // Connection
            mLoginPresenter.performLogin(email, password);
        }
    }

    private boolean loginValidation() {
        email = et_email.getText().toString();
        password = et_password.getText().toString();

        boolean cancel = false;
        if (TextUtils.isEmpty(password)) {
            et_password.setError(getString(R.string.error_field_required));
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            et_email.setError(getString(R.string.error_field_required));
            cancel = true;
        }

        return cancel;
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


    @Override
    public void loginValidations() {
        Toast.makeText(getApplicationContext(), "Please enter email and password", Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginSuccess() {
        // Go to HomeActivity
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFailure() {
        Toast.makeText(getApplicationContext(), "Login failure", Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginUnsuccessful() {
        Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_LONG).show();
    }
}