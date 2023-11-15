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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.polarys.appleitour.R;
//import com.polarys.appleitour.helper.FragmentHelper;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.Comment;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.view.activity.SeePostActivity;
import com.polarys.appleitour.view.customview.PublicationCustomView;
//import com.polarys.appleitour.view.fragment.PostFragment;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {
    private ArrayList<Post> data;
    private Context context;

    public PostAdapter(ArrayList<Post> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public PostAdapter.PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PostAdapter.PostHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item_publication, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostHolder holder, int position) {

            Post post = data.get(position);
            holder.user.setText(post.getUserName());
            holder.text.setText(post.getMessagePost());
           // holder.likes.setText(post.getLikes());
            holder.mainLayout.setOnClickListener(view -> {
                IntentHelper intentHelper = new IntentHelper((Activity) context, POST_SHARED);
                intentHelper.nextActivityObj(SeePostActivity.class,post);
            });
            holder.btnEdit.setOnClickListener(v -> {
            /*    FragmentHelper fragmentHelper = new FragmentHelper((Activity) context);
                fragmentHelper.nextFragmentObj(new PostFragment(),post);*/

            });

    }



    public class PostHolder extends RecyclerView.ViewHolder{
        TextView user, text, date,likes;
        Button btnLike,btnEdit;
        LinearLayout mainLayout;
        public PostHolder(@NonNull View view){
            super(view);
            this.user= view.findViewById(R.id.txt_publication_username);
            this.text= view.findViewById(R.id.txt_publication_message);
            this.btnEdit = view.findViewById(R.id.publication_placeholderedit);
          //  this.date= view.findViewById(R.id.publication_date);
            this.likes= view.findViewById(R.id.publication_likes);
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