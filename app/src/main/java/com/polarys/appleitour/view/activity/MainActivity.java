package com.polarys.appleitour.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.fragment.LoginFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedHelper sharedHelper = new SharedHelper(this);
        boolean logged = sharedHelper.GetKeepLogged();
      /*  IntentHelper inteontHelper = new IntentHelper(this);
        inteontHelper.nextActivity(PlaceholderActivity.class);*/
        if(logged){
            String token = sharedHelper.GetToken();
            ApiResponse response = new User().AutoLogin(token);
            if(response.getCode() == 200){
                IntentHelper intentHelper = new IntentHelper(this);
                intentHelper.nextActivity(PlaceholderActivity.class);
                finish();
            }else{
                finish();
                IntentHelper intentHelper = new IntentHelper(this);
                intentHelper.nextActivity(SignActivity.class);
            }
        }else {
            finish();
            IntentHelper intentHelper = new IntentHelper(this);
            intentHelper.nextActivity(SignActivity.class);
        }
    }
}