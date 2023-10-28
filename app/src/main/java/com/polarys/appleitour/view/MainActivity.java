package com.polarys.appleitour.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.view.fragment.BookApiFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentHelper intentHelper = new IntentHelper(this);
        intentHelper.nextActivity(LoginDummyActivity.class);
        /*
//        TextView txt = findViewById(R.id.test);



        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        ft.replace(R.id.your_placeholder, new BlankFragment());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();

        ApiThread apiThread = new ApiThread(GET,"entries","",null);
        Thread thread = new Thread(apiThread);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String value = apiThread.getJson();
        ft.replace(R.id.your_placeholder, new BlankFragment(value));*/


    }
}