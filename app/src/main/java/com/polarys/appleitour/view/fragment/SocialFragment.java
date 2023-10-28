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
import com.polarys.appleitour.view.adapter.PublicationAdapter;
import com.polarys.appleitour.viewmodel.SocialViewModel;

import java.util.ArrayList;

public class SocialFragment extends Fragment {

    private SocialViewModel viewModel;
    private RecyclerView recyclerView;
    private PublicationAdapter adapter;
    public SocialFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_main,container,false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(SocialViewModel.class);
        ArrayList<Post> posts = new ArrayList<>();
        recyclerView = getActivity().findViewById(R.id.recycler_social);
        adapter = new PublicationAdapter(posts,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        posts.clear();
        posts.addAll(viewModel.loadPosts());
        adapter.notifyDataSetChanged();
    }
}