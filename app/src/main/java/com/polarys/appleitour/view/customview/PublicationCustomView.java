package com.polarys.appleitour.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.polarys.appleitour.R;

public class PublicationCustomView extends LinearLayout{
    public PublicationCustomView(Context context) {
        super(context);
        init(null);
    }
    public PublicationCustomView(Context context,String name, String message) {
        super(context);
        init(null,name,message);
    }

    public PublicationCustomView(Context context, AttributeSet attrs){
        super(context,attrs);
        init(attrs);
    }


    private ImageView imageCircle;
    private EditText quantity;
    private ImageView icon;
    private TextView txt_name;
    private TextView txt_message;


    private void init(@Nullable AttributeSet attrs) {
        inflate(getContext(), R.layout.adapter_item_publication, this);
        imageCircle = findViewById(R.id.imageCircle_imageView);
        txt_name = findViewById(R.id.txt_publication_username);
        txt_message = findViewById(R.id.txt_publication_message);
        imageCircle.setImageDrawable(getResources().getDrawable(R.drawable.placeholder));
    }
    public void init(@Nullable AttributeSet attrs,@Nullable String name,@Nullable String message){
        inflate(getContext(), R.layout.adapter_item_publication, this);
        imageCircle = findViewById(R.id.imageCircle_imageView);
        txt_name = findViewById(R.id.txt_publication_username);
        txt_message = findViewById(R.id.txt_publication_message);
        imageCircle.setImageDrawable(getResources().getDrawable(R.drawable.placeholder));
        txt_name.setText(name);
        txt_message.setText(message);
    }
}