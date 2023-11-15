package com.polarys.appleitour.helper;

import android.app.Activity;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class IntentHelper extends AppCompatActivity {
    public final static String USER_SHARED = "USER_SHARED";
    public final static String BOOK_SHARED = "BOOK_SHARED";
    public final static String BOOK_KEY_SHARED = "BOOK_KEY_SHARED";
    public final static String SAVED_SHARED = "SAVED_BOOK_SHARED";
    public final static String POST_SHARED = "POST_SHARED";
    public final static String PUBLICATION_SHARED = "PUBLICATION_SHARED";
    public final static String ANNOTATION_SHARED = "ANNOTATION_SHARED";
    public final static String EDIT_SHARED = "EDIT_SHARED";
    public final static String MODE_SHARED = "MODE_SHARED";
    public final static String COMMENT_SHARED = "COMMENT_SHARED";
    public final static String FROM_ACTIVITY_KEY = "FROM_ACTIVITY_KEY";
    public final static String FROM_BOOKSEARCH = "FROM_BOOKSEARCH";
    public final static String FROM_SAVEDBOOK = "FROM_SAVEDBOOK";
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
//        activity.finish();
        Intent intent = new Intent(activity, nextScreen);
        activity.startActivity(intent);
    }
    public void nextActivity(Class nextScreen,String data){
  //      activity.finish();
        Intent intent = new Intent(activity, nextScreen);
        intent.putExtra(EXTRA_KEY, data);
        activity.startActivity(intent);
    }

    public void nextActivityObj(Class nextScreen,Object data){
       // activity.finish();
        Intent intent = new Intent(activity, nextScreen);
        intent.putExtra(EXTRA_KEY, (Serializable) data);
        activity.startActivity(intent);
    }


}
