package com.polarys.appleitour.view.activity;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.search.SearchBar;
import com.google.android.material.search.SearchView;
import com.google.android.material.snackbar.Snackbar;
import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.UIHelper;
import com.polarys.appleitour.view.fragment.BookSearchFragment;
import com.polarys.appleitour.view.fragment.PostFragment;
import com.polarys.appleitour.view.fragment.SavedBookFragment;
import com.polarys.appleitour.view.fragment.SocialFragment;
import com.polarys.appleitour.view.fragment.UserDataFragment;

public class PlaceholderActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private ProgressBar progressBar;
    private EditText searchBar;
    private FrameLayout viewPager;
    private UIHelper uiHelper;
    private TextView error_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeholder);
        toolbar = getSupportActionBar();
        viewPager = findViewById(R.id.placeholder_framelayout);
        View rootView = getWindow().getDecorView().getRootView();
        uiHelper = new UIHelper(this,rootView);
       // searchBar = findViewById(R.id.search_bar);
       // searchView = findViewById(R.id.search_view);

        SavedBookFragment savedBookFragment = new SavedBookFragment();
        BookSearchFragment bookSearchFragment = new BookSearchFragment();
        SocialFragment socialFragment = new SocialFragment();
        PostFragment postFragment = new PostFragment();
        UserDataFragment userDataFragment = new UserDataFragment();
        loadFragment(socialFragment);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigationview);
        bottomNavigationView.getMenu().findItem(R.id.nav_social).setChecked(true);
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

    public void showSnackBar(String message){
        uiHelper.showSnackBar(message);
    }
    public void showSnackBar(int message){
        uiHelper.showSnackBar(message);
    }
}