package com.polarys.appleitour.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.view.fragment.BookApiFragment;
import com.polarys.appleitour.view.fragment.SocialFragment;

public class PlaceholderActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeholder);
        toolbar = getSupportActionBar();
        BottomNavigationView bottomNavigationView1 = findViewById(R.id.bottom_navigationview);
      //  bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        loadFragment(new BookApiFragment());


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigationview);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.nav_saved) // saved bok
                loadFragment(new BookApiFragment());
            else if(item.getItemId() == R.id.nav_search)
                loadFragment(new BookApiFragment());
            else if(item.getItemId() == R.id.nav_social)
                loadFragment(new SocialFragment());
            else if(item.getItemId() == R.id.nav_user) //user page
                loadFragment(new BookApiFragment());
            return true;
        });
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.placeholder_framelayout,fragment);
        fragmentTransaction.commit();
    }

}