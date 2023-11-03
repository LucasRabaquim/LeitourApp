package com.polarys.appleitour.view.activity;

import static com.polarys.appleitour.helper.IntentHelper.EDIT_SHARED;
import static com.polarys.appleitour.helper.IntentHelper.POST_SHARED;
import static com.polarys.appleitour.helper.IntentHelper.PUBLICATION_SHARED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Button;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.Comment;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.view.adapter.AnnotationAdapter;
import com.polarys.appleitour.viewmodel.PublicationEditViewModel;
import com.polarys.appleitour.viewmodel.SocialViewModel;

public class PublicationEditActivity extends AppCompatActivity {

    PublicationEditViewModel viewModel;
    private Button btn_save_edit;
    private Button btn_cancel;
    private Object publication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_edit);
//        btn_save_edit = findViewById(R.id.btn_save_edit);
  //      btn_cancel = findViewById(R.id.btn_cancel);
        Comparable publication = (Comparable) getIntent().getSerializableExtra(POST_SHARED);
        boolean editMode = getIntent().getBooleanExtra(EDIT_SHARED,false);
/*
        if (element instanceof AnnotationAdapter)
            return TYPE_ANNOTATION;
        if (element instanceof Post)
            return TYPE_POST;
        if (element instanceof Comment)
            return TYPE_COMMENT;*/

       // Post post = (Post) getIntent().getSerializableExtra(POST_SHARED);
        viewModel = ViewModelProviders.of(this).get(PublicationEditViewModel.class);
        viewModel.SetToken(new SharedHelper(this).GetToken());

        /*Set Data*/

        btn_save_edit.setOnClickListener(v -> {
            if (publication instanceof Annotation){
                Post post = (Post) publication;
                if(editMode)
                    viewModel.UpdatePost(post);
                else
                    viewModel.CreatePost(post);
            }

            if (publication instanceof Post) {
                Comment comment = (Comment) publication;
            }
        });




    }
}