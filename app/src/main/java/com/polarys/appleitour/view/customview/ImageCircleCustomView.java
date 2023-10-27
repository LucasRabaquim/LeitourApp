package com.polarys.appleitour.view.customview;

import static androidx.databinding.DataBindingUtil.setContentView;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.polarys.appleitour.R;

public class ImageCircleCustomView extends LinearLayout {

    public ImageCircleCustomView(Context context) {
        super(context);
        init(null);
     //   setOnClickListener(this);

    }

    public ImageCircleCustomView(Context context, AttributeSet attrs){
        super(context);
        init(attrs);
    }
    private ImageView subtract;
    private EditText quantity;
    private ImageView imageCircle;


    private void init(@Nullable AttributeSet attrs) {
        inflate(getContext(), R.layout.image_circle, this);
        imageCircle = findViewById(R.id.imageCircle_imageView);
        imageCircle.setImageDrawable(getResources().getDrawable(R.drawable.placeholder));

        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.attr_image_circle);
        //imageCircle.setImageDrawable(attributes.getDrawable(R.styleable.BenefitView_image));
        attributes.recycle();

        imageCircle.setOnClickListener(view -> {

        });

    }
}