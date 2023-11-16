package com.polarys.appleitour.view.activity;

import static com.polarys.appleitour.api.ApiUtil.ObjectToString;
import static com.polarys.appleitour.helper.IntentHelper.POST_SHARED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Comment;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.view.adapter.CommentAdapter;
import com.polarys.appleitour.view.adapter.PostAdapter;
import com.polarys.appleitour.view.customview.PublicationCustomView;
import com.polarys.appleitour.view.customview.TextAreaCustomView;
import com.polarys.appleitour.viewmodel.SocialViewModel;

import java.util.ArrayList;

public class SeePostActivity extends AppCompatActivity {

    private SocialViewModel viewModel;
    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    private Button btn_create_comment;
    private LinearLayout textAreaCustomView;
    private TextView txt_name;
    private TextView txt_message;
    private TextView txt_likes;
    private Button botao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_post);
        Post post = (Post) getIntent().getSerializableExtra(POST_SHARED);
        viewModel = ViewModelProviders.of(this).get(SocialViewModel.class);
        txt_name = findViewById(R.id.txt_publication_username);
        txt_message = findViewById(R.id.txt_publication_message);
        txt_likes = findViewById(R.id.publication_likes);
        txt_name.setText(post.getUserName());
        txt_message.setText(post.getMessagePost());
        txt_likes.setText(post.getLikes() + "likes");

        SharedHelper sharedHelper = new SharedHelper(this);
        int id = sharedHelper.GetId();
        String token = sharedHelper.GetToken();

        ArrayList<Comment> comments = new ArrayList<Comment>(){};
        recyclerView = this.findViewById(R.id.recycler_social);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommentAdapter(comments,this,id,token);
        recyclerView.setAdapter(adapter);

        if(post.getId() != 0) {
            ArrayList<Comment> loadComments = viewModel.loadComments(post.getId());
            if(loadComments != null) {
                comments.clear();
                comments.addAll(loadComments);
                adapter.notifyDataSetChanged();
            }
        }


        textAreaCustomView = findViewById(R.id.textarea);//new TextAreaCustomView(this);
        btn_create_comment = findViewById(R.id.btn_create_comment);
        final boolean[] click = {true};
        btn_create_comment.setOnClickListener(v ->{
            if(click[0]){
                recyclerView.setVisibility(View.GONE);
                textAreaCustomView.setVisibility(View.VISIBLE);
            }else{
                recyclerView.setVisibility(View.VISIBLE);
                textAreaCustomView.setVisibility(View.GONE);
            }
            click[0] = !click[0];
        });
        botao = findViewById(R.id.btn_send_text);
        botao.setOnClickListener(v ->{
            EditText edit = findViewById(R.id.edit_message);
            String message = edit.getText().toString();

            Comment comment = new Comment(id, post.getId(), message);
            ApiResponse response = new Comment().PostComment(ObjectToString(comment),token);
            if(response.getCode() == 200 | response.getCode() == 201){
                comments.add(comment);
                adapter.notifyDataSetChanged();
                Toast.makeText(this,"Comentário Criado",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,response.getBody(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}