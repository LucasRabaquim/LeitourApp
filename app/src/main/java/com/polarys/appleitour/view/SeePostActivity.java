package com.polarys.appleitour.view;

import static com.polarys.appleitour.helper.IntentHelper.BOOK_SHARED;
import static com.polarys.appleitour.helper.IntentHelper.POST_SHARED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.polarys.appleitour.R;
import com.polarys.appleitour.model.Comment;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.view.adapter.PublicationAdapter;
import com.polarys.appleitour.viewmodel.SocialViewModel;

import java.util.ArrayList;

public class SeePostActivity extends AppCompatActivity {

    private SocialViewModel viewModel;
    private RecyclerView recyclerView;
    private PublicationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_post);
        Post post = (Post) getIntent().getSerializableExtra(POST_SHARED);
        viewModel = ViewModelProviders.of(this).get(SocialViewModel.class);
        viewModel.setContext(this);
        ArrayList<Comment> comments = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_social);
        adapter = new PublicationAdapter(comments,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        comments.clear();
        if(post.getId() != 0) {
            comments.addAll(viewModel.loadComments(post.getId()));
            adapter.notifyDataSetChanged();
        }
    }
}