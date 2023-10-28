package com.polarys.appleitour.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.polarys.appleitour.R;

public class IconCustomView extends LinearLayout {

    public IconCustomView(Context context) {
        super(context);
        init(null);
        //   setOnClickListener(this);

    }

    public IconCustomView(Context context, AttributeSet attrs){
        super(context);
        init(attrs);
    }
    private ImageView subtract;
    private EditText quantity;
    private ImageView icon;
    private TextView text;


    private void init(@Nullable AttributeSet attrs) {
        inflate(getContext(), R.layout.custom_icon, this);
        icon = findViewById(R.id.icon_button_image_view);
        icon.setImageDrawable(getResources().getDrawable(R.drawable.placeholder));
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.attr_custom_icon);
        //imageCircle.setImageDrawable(attributes.getDrawable(R.styleable.BenefitView_image));
        attributes.recycle();
/*
        imageCircle.setOnClickListener(view -> {

        });*/
    }
}