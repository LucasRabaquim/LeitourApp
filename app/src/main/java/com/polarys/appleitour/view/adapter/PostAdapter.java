package com.polarys.appleitour.view.adapter;

import static com.polarys.appleitour.helper.IntentHelper.POST_SHARED;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.polarys.appleitour.R;
import com.polarys.appleitour.api.ApiPost;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.view.activity.SeePostActivity;
import com.polarys.appleitour.view.fragment.PostFragment;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {
    private final ArrayList<Post> data;
    private final Context context;
    private final int id;
    private final String token;
    private Post post;



    public PostAdapter(ArrayList<Post> data, Context context, int id,String token) {
        this.data = data;
        this.context = context;
        this.id = id;
        this.token = token;
    }

    @Override
    public PostAdapter.PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PostAdapter.PostHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item_publication, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostHolder holder, int position) {



        post = data.get(position);
        holder.user.setText(post.GetUserName());
        holder.text.setText(post.GetMessagePost());
        holder.btnLike.setText("likes:" + post.GetLikes());
        holder.btnLike.setOnClickListener(v -> {
            ApiResponse response = new ApiPost().Like(post.GetId(),new SharedHelper(context).GetToken());
            Toast.makeText(context,response.getBody(),Toast.LENGTH_SHORT).show();
        });
        holder.mainLayout.setOnClickListener(view -> {
            IntentHelper intentHelper = new IntentHelper((Activity) context, POST_SHARED);
            intentHelper.nextActivityObj(SeePostActivity.class, post);
        });

        holder.btnOptions.setOnClickListener(view -> {

            PopupMenu popupMenu = new PopupMenu(context, view);
            popupMenu.inflate(R.menu.menu_publication_options); // Use your own menu resource file

            // Set up the click listener for menu items
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.publication_edit) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(POST_SHARED, post);
                        PostFragment fragment = new PostFragment();
                        fragment.setArguments(bundle);
                        ((AppCompatActivity) context).getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.placeholder_framelayout, fragment)
                                .commit();
                    return true;
                } else if (itemId == 2) {
                    return true;
                }//default intent
                return true;
            });

            // Show the PopupMenu
            popupMenu.show();


        }
        );


    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }




    public class PostHolder extends RecyclerView.ViewHolder {
        TextView user, text, date;
        Button btnLike;
        ImageButton btnOptions;
        LinearLayout mainLayout;

        public PostHolder(@NonNull View view) {
            super(view);
            this.user = view.findViewById(R.id.txt_publication_username);
            this.text = view.findViewById(R.id.txt_publication_message);
            this.btnOptions = view.findViewById(R.id.publication_options);
            //  this.date= view.findViewById(R.id.publication_date);
            this.btnLike = view.findViewById(R.id.publication_btn_like);
            mainLayout = view.findViewById(R.id.item_publication_layout);
        }
    }
}