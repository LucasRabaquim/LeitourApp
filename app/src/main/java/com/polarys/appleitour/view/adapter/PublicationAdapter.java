package com.polarys.appleitour.view.adapter;

import static com.polarys.appleitour.helper.IntentHelper.POST_SHARED;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.Comment;
import com.polarys.appleitour.model.Post;

import java.util.ArrayList;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.PublicationHolder> {
    private final int TYPE_POST = 0;
    private final int TYPE_COMMENT = 1;
    private final int TYPE_ANNOTATION = 2;

    private ArrayList<?> data;
    private Context context;

    public PublicationAdapter(ArrayList<?> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public PublicationAdapter.PublicationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PublicationAdapter.PublicationHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item_publication, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PublicationAdapter.PublicationHolder holder, int position) {
        if(getItemViewType(position) == TYPE_POST) {
            Post post = (Post) data.get(position);
            holder.user.setText(post.getUserName());
            holder.text.setText(post.getMessagePost());
            holder.date.setText(post.getLikes());
            holder.mainLayout.setOnClickListener(view -> {
                IntentHelper intentHelper = new IntentHelper((Activity) context, POST_SHARED);
                intentHelper.nextActivityObj(Comment.class,post);
            });
        }
        else {
            Comment comment = (Comment) data.get(position);
            holder.user.setText(comment.getUserName());
            holder.text.setText(comment.getMessagePost());
        }


    }



    public class PublicationHolder extends RecyclerView.ViewHolder{
        TextView user, text, date,likes;
        Button btnLike;
        LinearLayout mainLayout;
        public PublicationHolder(@NonNull View view){
            super(view);
         /*   this.user= view.findViewById(R.id.publication_username);
            this.text= view.findViewById(R.id.publication_text);
            this.date= view.findViewById(R.id.publication_date);
            this.likes= view.findViewById(R.id.publication_likes);
            this.btnLike =  view.findViewById(R.id.publication_like_button);*/
            mainLayout = view.findViewById(R.id.item_publication_layout);
        }
    }
    @Override
    public int getItemCount() { return data != null ? data.size() : 0; }

    @Override
    public int getItemViewType(int position) {
        Comparable element = (Comparable) data.get(position);
        if (element instanceof Annotation)
            return TYPE_ANNOTATION;
        if (element instanceof Post)
            return TYPE_POST;
        if (element instanceof Comment)
            return TYPE_COMMENT;
        return -1;
    }
}