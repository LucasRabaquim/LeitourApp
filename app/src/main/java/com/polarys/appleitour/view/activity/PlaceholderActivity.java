package com.polarys.appleitour.view.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.polarys.appleitour.R;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.view.fragment.BookSearchFragment;
import com.polarys.appleitour.view.fragment.PostFragment;
import com.polarys.appleitour.view.fragment.SavedBookFragment;
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
        loadFragment(new SocialFragment());


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigationview);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.nav_favorites) // saved bok
                loadFragment(new SavedBookFragment());
            else if(item.getItemId() == R.id.nav_seach_book)
                loadFragment(new BookSearchFragment());
            else if(item.getItemId() == R.id.nav_social)
                loadFragment(new SocialFragment());
            else if(item.getItemId() == R.id.nav_post)
                loadFragment(new PostFragment());
            else if(item.getItemId() == R.id.nav_user) //user page
                loadFragment(new BookSearchFragment());
            return true;
        });
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.placeholder_framelayout,fragment);
        fragmentTransaction.commit();
    }


}