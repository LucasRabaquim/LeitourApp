package com.polarys.appleitour.view.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.polarys.appleitour.R;
import com.polarys.appleitour.api.ApiUser;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.ApiResponse;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        SharedHelper sharedHelper = new SharedHelper(this);
        Class nextScreen;
        // Get User preference of login
        boolean logged = sharedHelper.GetKeepLogged();
        if (logged) { // If wanted to keep logged
            String token = sharedHelper.GetToken();
            ApiResponse response = new ApiUser().AutoLogin(token);
            if (response.getCode() == 200) // Success connecting to API
                nextScreen = HomeActivity.class;
            else // Internet, Autentication Error
                nextScreen = SignActivity.class;
        }
        else // User not want to keep logged
            nextScreen = SignActivity.class;
        IntentHelper intentHelper = new IntentHelper(this);
        intentHelper.nextActivity(nextScreen);
        finish();
    }
}