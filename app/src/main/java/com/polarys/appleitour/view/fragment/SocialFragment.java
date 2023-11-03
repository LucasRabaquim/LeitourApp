package com.polarys.appleitour.view.fragment;

import static androidx.databinding.DataBindingUtil.setContentView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.polarys.appleitour.R;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.view.adapter.PostAdapter;
import com.polarys.appleitour.viewmodel.SocialViewModel;

import java.util.ArrayList;

public class SocialFragment extends Fragment {

    private SocialViewModel viewModel;
    private RecyclerView recyclerView;
    private PostAdapter adapter;
    public SocialFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_social,container,false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(SocialViewModel.class);
        ArrayList<Post> posts = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_social);
        adapter = new PostAdapter(posts,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        ArrayList<Post> arrayList = viewModel.loadPosts();
        if(arrayList != null) {
            posts.clear();
            posts.addAll(arrayList);
            adapter.notifyDataSetChanged();
        }
    }
}