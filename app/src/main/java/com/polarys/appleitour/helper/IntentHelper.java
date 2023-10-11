package com.polarys.appleitour.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.polarys.appleitour.view.PlaceHolderActivity;

public class IntentHelper extends AppCompatActivity {
    public final static String USER_SHARED = "USER";
    public final static String BOOK_SHARED = "BOOK";
    static String EXTRA_KEY;

    private Activity activity;
    private Intent intent;

    public IntentHelper(Activity activity){
        this.activity = activity;
    }
    public IntentHelper(Activity activity, String key){
        this.activity = activity;
        EXTRA_KEY = key;
    }

    public void nextActivity(Class nextScreen){
        activity.finish();
        Intent intent = new Intent(activity, nextScreen);
        startActivity(intent);
    }
    public void nextActivity(Class nextScreen,String data){
        activity.finish();
        Intent intent = new Intent(activity, nextScreen);
        intent.putExtra(EXTRA_KEY, data);
        startActivity(intent);
    }


}
