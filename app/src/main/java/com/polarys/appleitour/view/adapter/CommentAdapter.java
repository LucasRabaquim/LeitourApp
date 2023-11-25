package com.polarys.appleitour.view.adapter;

import static com.polarys.appleitour.helper.IntentHelper.POST_SHARED;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.polarys.appleitour.R;
import com.polarys.appleitour.model.Comment;
import com.polarys.appleitour.view.fragment.PostFragment;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {
    private final int id;
    private final String token;
    private final ArrayList<Comment> data;
    private final Context context;

    public CommentAdapter(ArrayList<Comment> data, Context context, int id, String token) {
        this.data = data;
        this.context = context;
        this.id = id;
        this.token = token;
    }

    @Override
    public CommentAdapter.CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentAdapter.CommentHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item_publication, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentHolder holder, int position) {
        Comment comment = data.get(position);
        holder.user.setText(comment.getUserName());
        holder.text.setText(comment.getMessagePost());
        holder.like.setVisibility(View.GONE);

        holder.btnOptions.setOnClickListener(view -> {

            PopupMenu popupMenu = new PopupMenu(context, view);
            popupMenu.inflate(R.menu.menu_publication_options); // Use your own menu resource file

            // Set up the click listener for menu items
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.publication_edit) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    final EditText edittext = new EditText(context);
                    alert.setMessage("Enter Your Message");
                    alert.setTitle("Enter Your Title");

                    alert.setView(edittext);

                    alert.setPositiveButton("Yes Option", (dialog, whichButton) -> {

                        String YouEditTextValue = edittext.getText().toString();
                        Log.d("TAG", "onBindViewHolder: "+YouEditTextValue);
                    });

                    alert.setNegativeButton("No Option", (dialog, whichButton) -> {

                    });
                    alert.show();
                } else if (itemId == 2) {

                }//default intent
                return true;
            });

            // Show the PopupMenu
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

    public class CommentHolder extends RecyclerView.ViewHolder {
        TextView user, text;
        ImageButton btnOptions;
        ImageButton btnEdit;
        Button like;
        LinearLayout mainLayout;

        public CommentHolder(@NonNull View view) {
            super(view);
            this.user = view.findViewById(R.id.txt_publication_username);
            this.text = view.findViewById(R.id.txt_publication_message);
            this.btnEdit = view.findViewById(R.id.publication_edit);
            this.btnOptions = view.findViewById(R.id.publication_options);
            //  this.date= view.findViewById(R.id.publication_date);
            //  this.likes= view.findViewById(R.id.publication_likes);
            this.like = view.findViewById(R.id.publication_btn_like);
            mainLayout = view.findViewById(R.id.item_publication_layout);
        }
    }
}