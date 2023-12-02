package com.polarys.appleitour.view.activity;

import static androidx.databinding.DataBindingUtil.setContentView;

import static com.polarys.appleitour.helper.IntentHelper.EXTRA_KEY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.helper.UIHelper;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.SavedBook;
import com.polarys.appleitour.viewmodel.AnnotationViewModel;

public class AnnotationActivity extends AppCompatActivity {

    private AnnotationViewModel viewModel;
    private Button btn_post;
    private EditText edit_post;
    private TextView txt_cancelar;
    private Annotation annotation;
    private UIHelper uiHelper;
    private boolean edit_mode = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_post);
        viewModel = ViewModelProviders.of(this).get(AnnotationViewModel.class);
        btn_post = findViewById(R.id.btnSend);
        edit_post = findViewById(R.id.edit_post);
        txt_cancelar = findViewById(R.id.txt_cancelar);
        SharedHelper sharedHelper = new SharedHelper(this);
        int userId = sharedHelper.GetId();
        String token = sharedHelper.GetToken();
        Annotation savedAnnotation = null;
        SavedBook savedBook = null;
        int savedI;
        try {
            savedAnnotation = (Annotation) getIntent().getSerializableExtra(EXTRA_KEY);
            savedI = savedAnnotation.getSavedBookId();
        }catch(Exception e) {
            Log.d("Anno", "onCreate: "+e);
            savedBook = (SavedBook) getIntent().getSerializableExtra(EXTRA_KEY);
            savedI = savedBook.getId();
        }
        final int savedId = savedI;
        if(savedAnnotation != null){
            edit_mode = true;
            annotation = savedAnnotation;
            edit_post.setText(annotation.getAnnotationText());
        }
        txt_cancelar.setOnClickListener(v-> finish());

       // btn_delete.setOnClickListener(v -> {viewModel.DeleteAnnotation(annotation,token);});
        btn_post.setOnClickListener(v->{
            String message = edit_post.getText().toString();
            if(message.length() <= 5){
                Toast.makeText(this,"Escreva um pouco mais",Toast.LENGTH_SHORT).show();
                return;
            }
            if(edit_mode){
                annotation.setAnnotationText(message);
                ApiResponse response = viewModel.UpdateAnnotation(annotation,token);
                if(response.getCode() == 200 | response.getCode() == 201)
                    Toast.makeText(this,"Post alterado",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this,response.getBody(),Toast.LENGTH_SHORT).show();
            }
            else {
                Log.d("BAH","" +savedId);
                annotation = new Annotation(savedId, message);
                ApiResponse response = viewModel.CreateAnnotation(annotation, token);
                if (response.getCode() == 200 | response.getCode() == 201)
                    Toast.makeText(this, "Post Criado", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, response.getBody(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}