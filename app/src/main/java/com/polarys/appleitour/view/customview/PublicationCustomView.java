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

public class PublicationCustomView extends LinearLayout{
    public PublicationCustomView(Context context) {
        super(context);
        init(null);
        //   setOnClickListener(this);

    }

    public PublicationCustomView(Context context, AttributeSet attrs){
        super(context);
        init(attrs);
    }
    private ImageView imageCircle;
    private EditText quantity;
    private ImageView icon;
    private TextView text;


    private void init(@Nullable AttributeSet attrs) {
        inflate(getContext(), R.layout.adapter_item_publication, this);
        imageCircle = findViewById(R.id.imageCircle_imageView);
        imageCircle.setImageDrawable(getResources().getDrawable(R.drawable.placeholder));
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.attr_image_circle);
        //imageCircle.setImageDrawable(attributes.getDrawable(R.styleable.BenefitView_image));
        attributes.recycle();

    }
}