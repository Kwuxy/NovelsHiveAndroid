package com.example.novelshiveandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.novelshiveandroid.Globals;
import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.models.User;
import com.example.novelshiveandroid.presenters.ProfilePresenter;
import com.example.novelshiveandroid.viewModels.ProfileViewModel;
import com.example.novelshiveandroid.views.ProfileView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements ProfileView {

    private Toolbar myToolbar;
    private TextView tvUsername;
    private TextView tvEmail;
    private TextView tvDescription;

    ProfilePresenter mProfilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.configureToolbar();
        this.initUI();

        int userId = Globals.getCurrentToken().getUserId();
        mProfilePresenter = new ProfileViewModel(ProfileActivity.this);
        mProfilePresenter.getUserInfos(userId);
    }

    private void configureToolbar() {
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initUI() {
        this.tvUsername = findViewById(R.id.tv_username);
        this.tvEmail = findViewById(R.id.tv_email);
        this.tvDescription = findViewById(R.id.tv_description);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void displayUserProfile(User user) {
        this.tvUsername.setText(user.getUsername());
        this.tvEmail.setText(user.getEmail());
        String description = Globals.convertToText((ArrayList<Double>)user.getDescription().get("data"));
        this.tvDescription.setText(description);
    }
}
