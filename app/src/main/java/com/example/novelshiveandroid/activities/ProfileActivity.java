package com.example.novelshiveandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.models.User;
import com.example.novelshiveandroid.presenters.ProfilePresenter;
import com.example.novelshiveandroid.viewModels.ProfileViewModel;
import com.example.novelshiveandroid.views.ProfileView;

public class ProfileActivity extends AppCompatActivity implements ProfileView {

    private Toolbar myToolbar;

    ProfilePresenter mProfilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.configureToolbar();

        mProfilePresenter = new ProfileViewModel(ProfileActivity.this);
    }

    private void configureToolbar() {
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void displayUserProfile(User user) {

    }
}
