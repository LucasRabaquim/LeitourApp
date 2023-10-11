package com.polarys.appleitour.helper;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public final class ViewHelper extends AppCompatActivity {
    Activity activity;
    public ViewHelper(Activity activity){
        this.activity = activity;
    }
    public void setTextOfView(int id, String text){
        TextView textView = (TextView) activity.findViewById(id);
        textView.setText(text);
    }
    public String getTextOfView(int id){
        TextView textView = (TextView) activity.findViewById(id);
        String text = textView.getText().toString();
        if(text == "")
            Toast.makeText(activity,"Preencha o campo",Toast.LENGTH_SHORT).show();
        return null;
    }
    public String getTextOfEdit(int id){
        EditText textView = (EditText) activity.findViewById(id);
        String text = textView.getText().toString();
        if(text == "")
            Toast.makeText(activity,"Preencha o campo",Toast.LENGTH_SHORT).show();
        return null;
    }


    public boolean isEmpty(String data, String field){
        if(data == "")
            Toast.makeText(activity,"Preencha o campo "+field,Toast.LENGTH_SHORT).show();
        return (data == "");
    }
}
