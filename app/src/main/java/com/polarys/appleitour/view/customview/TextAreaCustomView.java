package com.polarys.appleitour.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.polarys.appleitour.R;

public class TextAreaCustomView extends LinearLayout implements View.OnClickListener {

    public TextAreaCustomView(Context context) {
        super(context);
        init(null);
        //   setOnClickListener(this);
    }

    public TextAreaCustomView(Context context, AttributeSet attrs){
        super(context);
        init(attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public Button btn_send;
    private EditText editText;
    private ImageButton icon;
    private TextView text;
    private String message;


    private void init(@Nullable AttributeSet attrs) {
        inflate(getContext(), R.layout.custom_text_area, null);
        editText = findViewById(R.id.edit_message);
        btn_send = findViewById(R.id.btn_send_text);
       // icon.setImageDrawable(getResources().getDrawable(R.drawable.placeholder));
     //   TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.attr_custom_icon);
        //imageCircle.setImageDrawable(attributes.getDrawable(R.styleable.BenefitView_image));
      //  attributes.recycle();
    }
    public String getMessage(){
        return editText.getText().toString();
    }

    @Override
    public void onClick(View v) {

    }
}