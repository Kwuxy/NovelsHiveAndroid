package com.example.novelshiveandroid.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.fragments.FavoriteFragment;
import com.example.novelshiveandroid.fragments.HomeFragment;
import com.example.novelshiveandroid.fragments.SearchFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar myToolbar;
    private DrawerLayout myDrawerLayout;
    private NavigationView myNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Home");

        loadFragment(new HomeFragment());

        this.configureNavigationView();
        this.configureDrawerLayout();
        this.configureBottomNavigationView();
    }

    private void configureBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.activity_home_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment;
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                myToolbar.setTitle("Home");
                                fragment = new HomeFragment();
                                loadFragment(fragment);
                                return true;
                            case R.id.action_favorite:
                                myToolbar.setTitle("Favorite");
                                fragment = new FavoriteFragment();
                                loadFragment(fragment);
                                return true;
                            case R.id.action_search:
                                myToolbar.setTitle("Search");
                                fragment = new SearchFragment();
                                loadFragment(fragment);
                                return true;
                        }
                        return false;
                    }
                }
        );
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_home_frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (this.myDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.myDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.activity_home_drawer_profile:
                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_home_drawer_my_stories:
                intent = new Intent(this, MyStoriesActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_home_drawer_parameters:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        this.myDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void configureDrawerLayout() {
        this.myDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, myDrawerLayout, myToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        myDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureNavigationView() {
        this.myNavigationView = findViewById(R.id.activity_home_nav_view);
        this.myNavigationView.setNavigationItemSelectedListener(this);
    }
}
