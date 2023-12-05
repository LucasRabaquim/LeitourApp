package com.polarys.appleitour.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.helper.UIHelper;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.activity.HomeActivity;
import com.polarys.appleitour.view.activity.StartActivity;
import com.polarys.appleitour.viewmodel.UserDataViewModel;

public class UserDataFragment extends Fragment{

    private UserDataViewModel viewModel;
    private User user;
    private TextView txt_user_name;
    private TextView txt_user_email;
    private ImageButton btn_options;
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
        SharedHelper sharedHelper = new SharedHelper(getContext());
        if(user == null)
            user = sharedHelper.GetUser();
        viewModel = ViewModelProviders.of(this).get(UserDataViewModel.class);
        tab_post = view.findViewById(R.id.tab_posts);
        txt_user_name = view.findViewById(R.id.txt_profile_name);
        txt_user_name.setText(user.GetNameUser());
        txt_user_email = view.findViewById(R.id.txt_profile_email);
        txt_user_email.setText(user.GetEmail());
        btn_options = view.findViewById(R.id.user_options);
        tab_post = view.findViewById(R.id.tab_posts);
      //  tab_follower = view.findViewById(R.id.tab_follower);
      //  tab_following = view.findViewById(R.id.tab_following);
        // Saved books, posts, following, followers
        int[] statistics = viewModel.getStatistics(user.GetEmail());


        tabLayout.getTabAt(0).setText("Posts\n"+statistics[0]);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               int position = tab.getPosition();
               switch (position){
                   case 0: loadFragment(new SocialFragment(user)); break;
                //   case 1: loadFragment(new UserFollowFragment(user, true)); break;
            //       case 2: loadFragment(new UserFollowFragment(user, false)); break;
                   default:loadFragment(new SocialFragment(user));
               }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        loadFragment(new SocialFragment(user));

        btn_options.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(getContext(), view);
            popupMenu.inflate(R.menu.menu_user_options);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.user_profile_exit) {
                    UIHelper uiHelper = new UIHelper(getContext());
                    AlertDialog.Builder builder = uiHelper.createDialog(R.string.logout_dialog_title, R.string.logout_diaog_message, R.string.string_dialog_option_cancel);
                    builder.setPositiveButton("Sair", (dialog, which) -> {
                        sharedHelper.Logout();
                        getActivity().finish();
                        IntentHelper intentHelper = new IntentHelper(getActivity());
                        intentHelper.nextActivity(StartActivity.class);
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
                return true;
            });
            popupMenu.show();
        });


    }
    public void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.userdata_framelayout,fragment);
        fragmentTransaction.commit();
    }
}