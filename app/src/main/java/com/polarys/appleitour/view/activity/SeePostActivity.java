package com.polarys.appleitour.view.activity;

import static com.polarys.appleitour.helper.IntentHelper.POST_SHARED;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
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
import com.google.android.material.snackbar.Snackbar;
import com.polarys.appleitour.R;
import com.polarys.appleitour.api.ApiComment;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Comment;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.adapter.CommentAdapter;
import com.polarys.appleitour.view.adapter.PostAdapter;
import com.polarys.appleitour.view.customview.PublicationCustomView;
import com.polarys.appleitour.view.customview.TextAreaCustomView;
import com.polarys.appleitour.viewmodel.CommentViewModel;
import com.polarys.appleitour.viewmodel.SocialViewModel;

import java.util.ArrayList;

public class SeePostActivity extends AppCompatActivity{

    private CommentViewModel viewModel;
    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    private Button btn_create_comment;
    private LinearLayout textAreaCustomView;
    private SwipeRefreshLayout refreshLayout;
    private TextView txt_name;
    private TextView txt_message;
    private TextView txt_likes;
    private Button botao;
    private ArrayList<Comment> comments;
    boolean isLoading = false;
    private int offset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_post);
        Post post = (Post) getIntent().getSerializableExtra(POST_SHARED);
        viewModel = ViewModelProviders.of(this).get(CommentViewModel.class);
        txt_name = findViewById(R.id.txt_publication_username);
        txt_message = findViewById(R.id.txt_publication_message);
        txt_likes = findViewById(R.id.publication_likes);
        txt_name.setText(post.GetUserName());
        txt_message.setText(post.GetMessagePost());
        txt_likes.setText(post.GetLikes() + "likes");

        SharedHelper sharedHelper = new SharedHelper(this);
        int id = sharedHelper.GetId();
        String token = sharedHelper.GetToken();

        comments = new ArrayList<Comment>(){};
        recyclerView = this.findViewById(R.id.recycler_social);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommentAdapter(comments,this,id,token);
        recyclerView.setAdapter(adapter);
        refreshLayout = findViewById(R.id.layout_refresh);
        refreshLayout.setOnRefreshListener(() ->{
            ArrayList<Comment> loadComments = viewModel.loadComments(post.GetId());
            resetAdapter(loadComments);
            offset = 0;
            refreshLayout.setRefreshing(false);
        });
        if(post.GetId() != 0) {
            ArrayList<Comment> loadComments = viewModel.loadComments(post.GetId());
            if (loadComments != null) {
                Log.d("TAG", "resetAdapter: "+ loadComments.toString());
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
            Comment comment = new Comment(id, post.GetId(), message,sharedHelper.GetUser());
            ApiResponse response = viewModel.CreateComment(comment,token);
            if(response.getCode() == 200 | response.getCode() == 201){
                comments.add(comment);
                adapter.notifyDataSetChanged();
                Toast.makeText(this,"Comentário Criado",Toast.LENGTH_SHORT).show();
                click[0] = !click[0];
            }
            else
                Toast.makeText(this,response.getBody(),Toast.LENGTH_SHORT).show();
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                offset = comments.size();
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == comments.size() - 1) {
                        Handler handler = new Handler();
                        handler.postDelayed(() -> {
                            ArrayList<Comment> loadComments = viewModel.loadComments(post.GetId(),offset);
                            if (loadComments != null)
                                comments.addAll(loadComments);
                            adapter.notifyDataSetChanged();
                            isLoading = false;
                        },2000);
                    }
                }

            }
        });
    }
    private void resetAdapter(ArrayList<Comment> _arrayList){
        if (_arrayList != null) {
            Log.d("TAG", "resetAdapter: "+ _arrayList.toString());
            comments.clear();
            comments.addAll(_arrayList);
            adapter.notifyDataSetChanged();
        }
    }



    public void showSnackBar(String message){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content).getRootView(), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}