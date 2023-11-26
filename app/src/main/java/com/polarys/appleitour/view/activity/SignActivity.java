package com.polarys.appleitour.view.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.view.fragment.LoginFragment;
import com.polarys.appleitour.view.fragment.RegisterFragment;
import com.polarys.appleitour.viewmodel.UserViewModel;

public class SignActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        SharedHelper sharedHelper = new SharedHelper(this);
        UserViewModel viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        if(sharedHelper.GetUser() == null) // Verify if its the user first time using the app
            loadFragment(new RegisterFragment(viewModel));
        else
            loadFragment(new LoginFragment(viewModel));
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.signin_framelayout,fragment);
        fragmentTransaction.commit();
    }
}
