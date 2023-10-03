package com.polarys.appleitour;

import static com.polarys.appleitour.ApiUtil.GET;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        String value = apiThread.getValue();
        ft.replace(R.id.your_placeholder, new BlankFragment(value));


    }
}