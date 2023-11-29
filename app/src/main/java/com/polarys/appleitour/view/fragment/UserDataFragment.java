package com.polarys.appleitour.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.activity.PlaceholderActivity;
import com.polarys.appleitour.viewmodel.UserDataViewModel;

public class UserDataFragment extends Fragment{

    private UserDataViewModel viewModel;
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
        SavedBookFragment savedBookFragment = new SavedBookFragment();
        SocialFragment socialFragment = new SocialFragment();
        PostFragment postFragment = new PostFragment();
        User user = new SharedHelper(getContext()).GetUser();
        UserFollowFragment userFollow = new UserFollowFragment(user,true);
        UserFollowFragment userFollowing = new UserFollowFragment(user,false);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int id = tab.getId();
                if (id == R.id.tab_saved) loadFragment(savedBookFragment);
                else if (id == R.id.tab_posts) loadFragment(postFragment);
                else if (id == R.id.tab_follow) loadFragment(userFollow);
                else if (id == R.id.tab_follower) loadFragment(userFollowing);
                else loadFragment(socialFragment);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }
    public void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.view_pager_user,fragment);
        fragmentTransaction.commit();
    }
}