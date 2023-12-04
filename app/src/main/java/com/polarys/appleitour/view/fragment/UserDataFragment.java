package com.polarys.appleitour.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.polarys.appleitour.R;
import com.polarys.appleitour.api.ApiUser;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.activity.PlaceholderActivity;
import com.polarys.appleitour.viewmodel.PostViewModel;
import com.polarys.appleitour.viewmodel.UserDataViewModel;

public class UserDataFragment extends Fragment{

    private UserDataViewModel viewModel;
    private User user;
    private com.google.android.material.tabs.TabItem tab_post,tab_following,tab_follower;

    public UserDataFragment(){}
    public UserDataFragment(User _user){user = _user;}
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_data, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TabLayout tabLayout = view.findViewById(R.id.tab_layout_user);
        if(user == null)
            user = new SharedHelper(getContext()).GetUser();
        viewModel = ViewModelProviders.of(this).get(UserDataViewModel.class);
        tab_post = view.findViewById(R.id.tab_posts);
        tab_follower = view.findViewById(R.id.tab_follower);
        tab_following = view.findViewById(R.id.tab_following);
        int[] statistics = viewModel.getStatistics(user.GetEmail());

        tab_post.setOnClickListener(v -> loadFragment(new SocialFragment(user)));
        tab_follower.setOnClickListener(v -> loadFragment(new UserFollowFragment(user,true)));
        tab_following.setOnClickListener(v -> loadFragment(new UserFollowFragment(user,false)));

    }
    public void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.view_pager_user,fragment);
        fragmentTransaction.commit();
    }
}