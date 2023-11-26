package com.polarys.appleitour.view.activity;

import static androidx.databinding.DataBindingUtil.setContentView;

import static com.polarys.appleitour.api.ApiUtil.ObjectToString;
import static com.polarys.appleitour.helper.IntentHelper.ANNOTATION_SHARED;
import static com.polarys.appleitour.helper.IntentHelper.BOOK_SHARED;
import static com.polarys.appleitour.helper.IntentHelper.EXTRA_KEY;
import static com.polarys.appleitour.helper.IntentHelper.POST_SHARED;
import static com.polarys.appleitour.helper.IntentHelper.SAVED_SHARED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.polarys.appleitour.R;
import com.polarys.appleitour.api.ApiAnnotation;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Comment;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.model.SavedBook;
import com.polarys.appleitour.view.adapter.PostAdapter;
import com.polarys.appleitour.viewmodel.AnnotationViewModel;
import com.polarys.appleitour.viewmodel.SocialViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class AnnotationActivity extends AppCompatActivity {

    private AnnotationViewModel viewModel;
    private Button btn_post,btn_delete;
    private EditText edit_post;
    private TextView txt_cancelar;
    private Annotation annotation;
    private boolean edit_mode = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_post);
        viewModel = ViewModelProviders.of(this).get(AnnotationViewModel.class);
        btn_post = findViewById(R.id.btnPost);
        edit_post = findViewById(R.id.edit_post);
        btn_delete = findViewById(R.id.btnDeletePost);
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
            btn_delete.setVisibility(View.VISIBLE);
        }
        txt_cancelar.setOnClickListener(v-> finish());

        btn_delete.setOnClickListener(v -> {viewModel.DeleteAnnotation(annotation,token);});
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