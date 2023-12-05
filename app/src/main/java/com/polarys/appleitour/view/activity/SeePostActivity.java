package com.polarys.appleitour.view.activity;

import static com.polarys.appleitour.helper.IntentHelper.POST_SHARED;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.polarys.appleitour.R;
import com.polarys.appleitour.api.ApiPost;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.helper.UIHelper;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Comment;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.view.adapter.CommentAdapter;
import com.polarys.appleitour.viewmodel.CommentViewModel;

import java.util.ArrayList;

public class SeePostActivity extends AppCompatActivity{

    private CommentViewModel viewModel;
    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    private Button btn_create_comment;
    private LinearLayout textAreaCustomView;
    private SwipeRefreshLayout refreshLayout;
    private TextView txt_name,txt_publication_useremail,txt_message,txt_date;
    private MaterialButton btn_like, btn_send_comment, btn_comments;
    private ArrayList<Comment> comments;
    private boolean isLoading = false;
    private UIHelper uiHelper;
    private ApiPost apiPost = new ApiPost();
    private int offset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_post);
        Post post = (Post) getIntent().getSerializableExtra(POST_SHARED);
        viewModel = ViewModelProviders.of(this).get(CommentViewModel.class);
        SharedHelper sharedHelper = new SharedHelper(this);
        int id = sharedHelper.GetId();
        String token = sharedHelper.GetToken();
        declareUi(post,token);
        uiHelper = new UIHelper(this,this.getWindow().getDecorView().getRootView());


        comments = new ArrayList<Comment>(){};
        recyclerView = this.findViewById(R.id.recycler_social);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommentAdapter(comments,this,id,token);
        recyclerView.setAdapter(adapter);
        refreshLayout = findViewById(R.id.layout_refresh);

        refreshLayout.setOnRefreshListener(() ->{ // On pull refresh
            ArrayList<Comment> loadComments = viewModel.loadComments(post.GetId());
            resetAdapter(loadComments);
            offset = 0;
            refreshLayout.setRefreshing(false);
        });

        if(post.GetId() != 0) {
            ArrayList<Comment> loadComments = viewModel.loadComments(post.GetId());
            if (loadComments != null) {
                comments.clear();
                comments.addAll(loadComments);
                adapter.notifyDataSetChanged();
            }
        }

        textAreaCustomView = findViewById(R.id.textarea);
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
        btn_send_comment = findViewById(R.id.btn_send_text);
        btn_send_comment.setOnClickListener(v ->{
            EditText edit = findViewById(R.id.edit_message);
            String message = edit.getText().toString();
            Comment comment = new Comment(id, post.GetId(), message,sharedHelper.GetUser());
            ApiResponse response = viewModel.CreateComment(comment,token);
            if(response.getCode() == 200 | response.getCode() == 201){
                comments.add(comment);
                adapter.notifyDataSetChanged();
                showSnackBar(R.string.string_comment_created);
                click[0] = !click[0];
            }
            else
                showSnackBar(response.getBody());
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

    private void declareUi(Post post,String token){
        txt_name = findViewById(R.id.txt_publication_username);
        txt_publication_useremail = findViewById(R.id.txt_publication_useremail);
        txt_date = findViewById(R.id.txt_publication_date);
        txt_message = findViewById(R.id.txt_publication_message);
        txt_publication_useremail = findViewById(R.id.txt_publication_useremail);
        btn_comments = findViewById(R.id.publication_comments_number);
        btn_like = findViewById(R.id.publication_btn_like);

        txt_name.setText(post.GetUserName());
        txt_publication_useremail.setText(post.getEmail());
        txt_date.setText(post.GetCreatedDate());
        txt_message.setText(post.GetMessagePost());
        btn_like.setText("Likes:" + post.GetLikes());
        if(post.GetLiked())
            btn_like.setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_favorite_24));
        btn_comments.setText(String.valueOf(post.GetCommentNumber()));
        btn_like.setOnClickListener(v -> {
            ApiResponse response = apiPost.Like(post.GetId(), token);
            int likes = post.GetLikes() + ((post.GetLiked()) ? -1 : +1);
            post.SetLikes(likes);
            btn_like.setText("Likes: " + likes);
            post.SetLiked(!post.GetLiked());
            if(post.GetLiked())
                btn_like.setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_favorite_24));
            else
                btn_like.setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_favorite_border_24));
        });


    }
    private void resetAdapter(ArrayList<Comment> _arrayList){
        if (_arrayList != null) {
            comments.clear();
            comments.addAll(_arrayList);
            adapter.notifyDataSetChanged();
        }
    }
    public void showSnackBar(String message){
        uiHelper.showSnackBar(message);
    }
    public void showSnackBar(int message){
        uiHelper.showSnackBar(message);
    }

}