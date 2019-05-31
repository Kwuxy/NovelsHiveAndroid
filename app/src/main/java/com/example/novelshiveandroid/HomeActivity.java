package com.example.novelshiveandroid;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity {

    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Home");

        loadFragment(new HomeFragment());

        this.configureBottomNavigationView();
    }

    private void configureBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation);
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
                                break;
                            case R.id.action_favorite:
                                myToolbar.setTitle("Favorite");
                                fragment = new FavoriteFragment();
                                loadFragment(fragment);
                                break;
                            case R.id.action_search:
                                myToolbar.setTitle("Search");
                                fragment = new SearchFragment();
                                loadFragment(fragment);
                                break;
                        }
                        return false;
                    }
                }
        );
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
