package com.polarys.appleitour.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.polarys.appleitour.R;

public class ProgressCustomView extends LinearLayout {

    public ProgressCustomView(Context context) {
        super(context);
        // init(null);
        //   setOnClickListener(this);

    }

    public ProgressCustomView(Context context, AttributeSet attrs){
        super(context);
        init(attrs);
    }



    private void init(@Nullable AttributeSet attrs) {
        inflate(getContext(), R.layout.custom_progress, this);

    }

   /* public void init(@Nullable Bitmap bitmap) {
        inflate(getContext(), R.layout.custom_progress, this);
        imageCircle = findViewById(R.id.imageCircle_imageView);
        imageCircle.setImageBitmap(bitmap);
    }
    public void setImage(){}*/
}
