package com.polarys.appleitour.view.adapter;

import static com.polarys.appleitour.helper.IntentHelper.POST_SHARED;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.activity.SeePostActivity;
import com.polarys.appleitour.view.fragment.PostFragment;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private final ArrayList<User> data;
    private final Context context;
    private User post;


    public UserAdapter(ArrayList<User> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public UserAdapter.UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new UserAdapter.UserHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_publication, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserHolder holder, int position) {


        post = data.get(position);
      /* holder.user.setText(post.GetUserName());
        holder.text.setText(post.GetMessagePost());
        holder.btnLike.setText("likes:" + post.GetLikes());*/
        holder.btnLike.setOnClickListener(v -> {
            ApiResponse response = new ApiPost().Like(post.GetId(), new SharedHelper(context).GetToken());
            Toast.makeText(context, response.getBody(), Toast.LENGTH_SHORT).show();
        });
        holder.mainLayout.setOnClickListener(view -> {
            IntentHelper intentHelper = new IntentHelper((Activity) context, POST_SHARED);
            intentHelper.nextActivityObj(SeePostActivity.class, post);
        });

    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        TextView user, text, date;
        Button btnLike;
        ImageButton btnOptions;
        LinearLayout mainLayout;

        public UserHolder(@NonNull View view) {
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
