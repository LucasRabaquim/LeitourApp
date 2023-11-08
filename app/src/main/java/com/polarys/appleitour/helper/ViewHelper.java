package com.polarys.appleitour.helper;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public abstract class ViewHelper extends AppCompatActivity {


    public static void setTextOfView(Activity activity, int id, String text){
        TextView textView = (TextView) activity.findViewById(id);
        textView.setText(text);
    }
    public String getTextOfView(Activity activity, int id){
        TextView textView = (TextView) activity.findViewById(id);
        String text = textView.getText().toString();
        if(text == "")
            Toast.makeText(activity,"Preencha o campo",Toast.LENGTH_SHORT).show();
        return null;
    }
    public static String getTextOfEdit(Activity activity, int id){
        EditText textView = activity.findViewById(id);
        try {
            return textView.getText().toString();
        }catch(Exception e){
            return "";
        }
    }


    public static boolean isEmpty(Activity activity, String data, String field){
        if(data == "")
            Toast.makeText(activity,"Preencha o campo "+field,Toast.LENGTH_SHORT).show();
        return (data == "");
    }
    public static boolean isDifferent(Activity activity, String data, String field, String compare, String fieldCompare){
        if(data != compare)
            Toast.makeText(activity,"Preencha o campo "+field+" precisa ser igual ao campo "+
                    fieldCompare,Toast.LENGTH_SHORT).show();
        return (data != compare);
    }
}
