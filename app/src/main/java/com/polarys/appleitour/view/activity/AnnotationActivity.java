package com.polarys.appleitour.view.activity;

import static com.polarys.appleitour.helper.IntentHelper.ANNOTATION_SHARED;
import static com.polarys.appleitour.helper.IntentHelper.EDIT_SHARED;
import static com.polarys.appleitour.helper.IntentHelper.SAVED_SHARED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.SavedBook;
import com.polarys.appleitour.viewmodel.AnnotationViewModel;
import com.polarys.appleitour.viewmodel.SignViewModel;

public class AnnotationActivity extends AppCompatActivity {

    private Annotation annotation;
    private SavedBook book;
    private Boolean editMode;
    private Button save;
    private Button cancel;
    private EditText text;
    private AnnotationViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        save = findViewById(R.id.btn_save);
        cancel = findViewById(R.id.btn_cancel);
        text = findViewById(R.id.edit_annotation);
        viewModel = ViewModelProviders.of(this).get(AnnotationViewModel.class);
        try {
            annotation = (Annotation) getIntent().getSerializableExtra(ANNOTATION_SHARED);
            editMode = getIntent().getBooleanExtra(EDIT_SHARED,false);
        } catch (Exception e) {
            annotation = null;
        }
        book = (SavedBook) getIntent().getSerializableExtra(SAVED_SHARED);
        save.setOnClickListener(v ->{
            String message = text.getText().toString();
            Annotation annotation = new Annotation(book.getId(),message);
            String token = new SharedHelper(this).GetToken();
            if(editMode)
                viewModel.addAnnotation(annotation,token);
            else
                viewModel.addAnnotation(annotation,token);
        });
        cancel.setOnClickListener(v -> finish());
    }
}