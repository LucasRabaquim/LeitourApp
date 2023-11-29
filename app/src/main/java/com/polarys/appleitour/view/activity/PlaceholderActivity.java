package com.polarys.appleitour.view.activity;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.polarys.appleitour.R;
import com.polarys.appleitour.view.fragment.BookSearchFragment;
import com.polarys.appleitour.view.fragment.PostFragment;
import com.polarys.appleitour.view.fragment.SavedBookFragment;
import com.polarys.appleitour.view.fragment.SocialFragment;
import com.polarys.appleitour.view.fragment.UserDataFragment;

public class PlaceholderActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private ProgressBar progressBar;
    private FrameLayout viewPager;
    private TextView error_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeholder);
        toolbar = getSupportActionBar();
        viewPager = findViewById(R.id.placeholder_framelayout);

        SavedBookFragment savedBookFragment = new SavedBookFragment();
        BookSearchFragment bookSearchFragment = new BookSearchFragment();
        SocialFragment socialFragment = new SocialFragment();
        PostFragment postFragment = new PostFragment();
        UserDataFragment userDataFragment = new UserDataFragment();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigationview);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_favorites) loadFragment(savedBookFragment);
            else if (id == R.id.nav_seach_book) loadFragment(bookSearchFragment);
            else if (id == R.id.nav_social) loadFragment(socialFragment);
            else if (id == R.id.nav_post) loadFragment(postFragment);
            else if (id == R.id.nav_user) loadFragment(userDataFragment);
            else loadFragment(socialFragment);
            return true;
        });
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.placeholder_framelayout,fragment);
        fragmentTransaction.commit();
    }
}