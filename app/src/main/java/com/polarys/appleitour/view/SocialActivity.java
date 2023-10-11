package com.polarys.appleitour.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.polarys.appleitour.R;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.view.adapter.ApiBookAdapter;
import com.polarys.appleitour.view.adapter.PublicationAdapter;
import com.polarys.appleitour.viewmodel.SignViewModel;
import com.polarys.appleitour.viewmodel.SocialViewModel;

import java.util.ArrayList;

public class SocialActivity extends AppCompatActivity {

    private SocialViewModel viewModel;
    private RecyclerView recyclerView;
    private PublicationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        viewModel = ViewModelProviders.of(this).get(SocialViewModel.class);
        viewModel.setContext(this);
        ArrayList<Post> posts = new ArrayList<Post>();
        recyclerView = findViewById(R.id.recycler_social);
        adapter = new PublicationAdapter(posts,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        posts.clear();
        posts.addAll(viewModel.load());
        adapter.notifyDataSetChanged();

    }
}