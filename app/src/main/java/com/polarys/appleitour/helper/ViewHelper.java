package com.polarys.appleitour.helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class ViewHelper extends AppCompatActivity {
    public static void setTextOfView(Activity activity, int id, String text){
        TextView textView = (TextView) activity.findViewById(id);
        textView.setText(text);
    }
    private Activity activity;
    public ViewHelper(Activity _activity){
        this.activity = _activity;
    }
    public void setTextOfViewAppend(int id, int prefix, String text){
        TextView textView = activity.findViewById(id);
        try {
            if (text.isEmpty())
                textView.setVisibility(View.GONE);
            textView.setText(activity.getResources().getString(prefix) + text);
        }catch (Exception e){

        }
    }

}
