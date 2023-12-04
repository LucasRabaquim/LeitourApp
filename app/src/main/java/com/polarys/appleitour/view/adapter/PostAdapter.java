package com.polarys.appleitour.view.adapter;

import static com.polarys.appleitour.helper.IntentHelper.POST_SHARED;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.polarys.appleitour.R;
import com.polarys.appleitour.api.ApiPost;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.helper.UIHelper;
import com.polarys.appleitour.interfaces.IPost;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.view.activity.PlaceholderActivity;
import com.polarys.appleitour.view.activity.SeePostActivity;
import com.polarys.appleitour.view.fragment.PostFragment;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {
    private final ArrayList<Post> data;
    private final Context context;
    private final int id;
    private final String token;
    private ApiPost apiPost = new ApiPost();
    private SharedHelper sharedHelper;



    public PostAdapter(ArrayList<Post> data, Context context, int id, String token) {
        this.data = data;
        this.context = context;
        this.id = id;
        this.token = token;
        sharedHelper = new SharedHelper(context);
    }

    @Override
    public PostAdapter.PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PostAdapter.PostHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_publication, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostHolder holder, int position) {
        Post post = data.get(position);
        holder.user.setText(post.GetUserName());
        holder.email.setText(post.getEmail());
        holder.date.setText(post.GetCreatedDate());
        holder.text.setText(post.GetMessagePost());
        holder.btnLike.setText("Likes:" + post.GetLikes());
        if(post.GetLiked())
            holder.btnLike.setBackgroundResource(R.drawable.baseline_favorite_24);
        holder.btnComments.setText(String.valueOf(post.GetCommentNumber()));
        holder.btnLike.setOnClickListener(v -> {
            ApiResponse response = apiPost.Like(post.GetId(), token);
            if(response.getCode() == (200 | 201)){
                int likes = post.GetLikes() + ((post.GetLiked()) ? -1 : +1);
                holder.btnLike.setText("Likes: " + likes);
                post.SetLiked(!post.GetLiked());
                if(post.GetLiked())
                    holder.btnLike.setBackgroundResource(R.drawable.baseline_favorite_24);
                else
                    holder.btnLike.setBackgroundResource(R.drawable.baseline_favorite_border_24);
            }

        });

        holder.mainLayout.setOnClickListener(view -> {
            IntentHelper intentHelper = new IntentHelper((Activity) context, POST_SHARED);
            intentHelper.nextActivityObj(SeePostActivity.class, post);
        });

        if(post.GetUserId() == id)
            holder.btnOptions.setVisibility(View.VISIBLE);
        holder.btnOptions.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(context, view);
            popupMenu.inflate(R.menu.menu_publication_options);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.publication_edit) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(POST_SHARED, post);
                    PostFragment fragment = new PostFragment();
                    fragment.setArguments(bundle);
                    ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.placeholder_framelayout, fragment).commit();
                } else if (itemId == R.id.publication_delete) {
                    UIHelper uiHelper = new UIHelper(context);
                    AlertDialog.Builder builder = uiHelper.createDialog(R.string.string_post_delete_dialog_title,R.string.string_post_delete_dialog_message,R.string.string_dialog_option_cancel);
                    builder.setPositiveButton("Confirmar", (dialog, which) -> {
                        int success =  apiPost.DeletePost(post,token).getCode();
                        int message = (success != (200 | 201)) ? R.string.string_post_delete_success : R.string.string_post_delete_error;
                        ((PlaceholderActivity) context).showSnackBar(message);
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                return true;
            });
            popupMenu.show();
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

    public class PostHolder extends RecyclerView.ViewHolder {
        TextView user, email,text, date;
        Button btnLike, btnComments;
        ImageButton btnOptions;
        LinearLayout mainLayout;

        public PostHolder(@NonNull View view) {
            super(view);
            this.user = view.findViewById(R.id.txt_publication_username);
            this.email = view.findViewById(R.id.txt_publication_useremail);
            this.text = view.findViewById(R.id.txt_publication_message);
            this.date = view.findViewById(R.id.txt_publication_date);
            this.btnOptions = view.findViewById(R.id.publication_options);
            this.btnComments = view.findViewById(R.id.publication_comments_number);
            this.btnLike = view.findViewById(R.id.publication_btn_like);
            mainLayout = view.findViewById(R.id.item_publication_layout);
        }
    }
}