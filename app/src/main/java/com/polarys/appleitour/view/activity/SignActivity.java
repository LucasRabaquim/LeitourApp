package com.polarys.appleitour.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.polarys.appleitour.R;
import com.polarys.appleitour.view.fragment.BookSearchFragment;
import com.polarys.appleitour.view.fragment.LoginFragment;
import com.polarys.appleitour.view.fragment.SavedBookFragment;
import com.polarys.appleitour.view.fragment.SocialFragment;

public class SignActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        loadFragment(new LoginFragment());
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.signin_framelayout,fragment);
        fragmentTransaction.commit();
    }
}
