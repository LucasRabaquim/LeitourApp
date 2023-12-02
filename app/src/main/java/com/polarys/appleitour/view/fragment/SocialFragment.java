package com.polarys.appleitour.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
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
    private SwipeRefreshLayout refreshLayout;
    private ArrayList<Post> arrayList;
    private ArrayList<Post> posts = new ArrayList<>();
    boolean isLoading = false;
    private User user = null;
    private int offset;
    private PostAdapter adapter;

    public SocialFragment() {    }
    public SocialFragment(User user) {
        this.user = user;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_social, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(SocialViewModel.class);
        refreshLayout = view.findViewById(R.id.layout_refresh);
        recyclerView = view.findViewById(R.id.recycler_social);
        SharedHelper sharedHelper = new SharedHelper(getContext());
        String token = sharedHelper.GetToken();
        int id = sharedHelper.GetId();
        adapter = new PostAdapter(posts, getActivity(), id, token);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        arrayList = getAdapter(token);
        resetAdapter(arrayList);
        refreshLayout.setOnRefreshListener(() ->{ // Refresh on pull
            resetAdapter(getAdapter(token));
            offset = 0;
            refreshLayout.setRefreshing(false);
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) { // Infinite Scroll
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                offset = posts.size();
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == arrayList.size() - 1) {
                      new Handler().postDelayed(() -> {
                            arrayList = getAdapter(token, offset);
                            if (arrayList != null)
                                posts.addAll(arrayList);
                            adapter.notifyDataSetChanged();
                            isLoading = false;
                        },2000);
                    }
                }

            }
        });
    }
    private ArrayList<Post> getAdapter(String _token){
        return getAdapter(_token,0);
    }
    private ArrayList<Post> getAdapter(String _token, int offset){
        return (user == null) ? viewModel.loadPosts(_token,offset) :
                viewModel.loadPostsByEmail(_token,user.GetEmail(), offset);
    }
    private void resetAdapter(ArrayList<Post> _arrayList){
        if (_arrayList != null) {
            posts.clear();
            posts.addAll(_arrayList);
            adapter.notifyDataSetChanged();
        }
    }
}