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
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.adapter.PostAdapter;
import com.polarys.appleitour.viewmodel.SocialViewModel;

import java.util.ArrayList;

public class SocialFragment extends Fragment {

    private SocialViewModel viewModel;

    private RecyclerView recyclerView;
    private User user = null;
    private PostAdapter adapter;
    public SocialFragment(){}
    public SocialFragment(User user){this.user = user;}

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
        SharedHelper sharedHelper = new SharedHelper(getContext());
        String token = sharedHelper.GetToken();
        int id = sharedHelper.GetId();
        adapter = new PostAdapter(posts,getActivity(),id,token);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        ArrayList<Post> arrayList;
        if(user == null)
            arrayList = viewModel.loadPosts(0);
        else
            arrayList = viewModel.loadPostsByEmail(user.GetEmail(),0);
        if(arrayList != null) {
            posts.clear();
            posts.addAll(arrayList);
            adapter.notifyDataSetChanged();
        }
    }
}