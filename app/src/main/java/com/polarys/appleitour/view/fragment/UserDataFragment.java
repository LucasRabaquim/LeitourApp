package com.polarys.appleitour.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.viewmodel.UserDataViewModel;

public class UserDataFragment extends Fragment {

    private UserDataViewModel viewModel;

    public static UserDataFragment newInstance() {
        return new UserDataFragment();
    }

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
        viewModel = ViewModelProviders.of(this).get(UserDataViewModel.class);
        SharedHelper sharedHelper = new SharedHelper(getContext());
        User user = sharedHelper.GetUser();

        /*ArrayList<Post> posts = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_social);
        adapter = new PublicationAdapter(posts,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        ArrayList<Post> arrayList = viewModel.loadPosts();
        if(arrayList != null) {
            posts.clear();
            posts.addAll(arrayList);
            adapter.notifyDataSetChanged();
        }*/
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.menu_user_configuration, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.message)
            Toast.makeText(getContext(), "Shows share icon", Toast.LENGTH_SHORT).show();
        /*switch (item.getItemId()) {
            case R.id.message:

                return true;

            case R.id.picture:
                Toast.makeText(getContext(), "Shows image icon", Toast.LENGTH_SHORT).show();
                //startActivity(i2);
                return (true);

            case R.id.mode:
                Toast.makeText(getContext(), "Shows call icon", Toast.LENGTH_SHORT).show();
                return (true);

            case R.id.about:
                Toast.makeText(getContext(), "calculator menu", Toast.LENGTH_SHORT).show();
                return (true);

            case R.id.exit:
                getActivity().finish();
                return (true);
        }*/
        return (super.onOptionsItemSelected(item));
    }

}