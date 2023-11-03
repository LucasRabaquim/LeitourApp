package com.polarys.appleitour.view.adapter;

import static com.polarys.appleitour.helper.IntentHelper.POST_SHARED;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.model.Comment;
import com.polarys.appleitour.view.activity.SeePostActivity;
import com.polarys.appleitour.view.customview.PublicationCustomView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {
    private ArrayList<Comment> data;
    private Context context;

    public CommentAdapter(ArrayList<Comment> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public CommentAdapter.CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PublicationCustomView itemView = new PublicationCustomView(parent.getContext());
        return new CommentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentHolder holder, int position) {
            Comment comment = data.get(position);
            holder.user.setText(comment.getUserName());
            holder.text.setText(comment.getMessagePost());
           // holder.likes.setText(post.getLikes());
    }



    public class CommentHolder extends RecyclerView.ViewHolder{
        TextView user, text;
        Button btnLike;
        LinearLayout mainLayout;
        public CommentHolder(@NonNull View view){
            super(view);
            this.user= view.findViewById(R.id.txt_publication_username);
            this.text= view.findViewById(R.id.txt_publication_message);
          //  this.date= view.findViewById(R.id.publication_date);
          //  this.likes= view.findViewById(R.id.publication_likes);
            this.btnLike =  view.findViewById(R.id.publication_like_button);
            mainLayout = view.findViewById(R.id.item_publication_layout);
        }
    }
    @Override
    public int getItemCount() { return data != null ? data.size() : 0; }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}