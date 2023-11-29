package com.polarys.appleitour.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.adapter.PostAdapter;
import com.polarys.appleitour.view.adapter.UserAdapter;
import com.polarys.appleitour.viewmodel.UserDataViewModel;

import java.util.ArrayList;

public class UserFollowFragment extends Fragment {

    private UserDataViewModel viewModel;
    private User user;
    private boolean followingOrFollower;

    public UserFollowFragment(User _user,boolean follow){
        user = _user;
        followingOrFollower = follow;
    }

    // TODO: USE RIGHT fragments and layouts
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_social, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(UserDataViewModel.class);
        ArrayList<User> users = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_social);
        UserAdapter adapter = new UserAdapter(users, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        ArrayList<User> arrayList;
        arrayList = (followingOrFollower) ? viewModel.loadFollowing(user.GetEmail(),0) :
                viewModel.loadFollowers(user.GetEmail(), 0);
        if (arrayList != null) {
            users.clear();
            users.addAll(arrayList);
            adapter.notifyDataSetChanged();
        }
    }
}