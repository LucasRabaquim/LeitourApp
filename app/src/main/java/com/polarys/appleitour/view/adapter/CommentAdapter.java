package com.polarys.appleitour.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.polarys.appleitour.R;
import com.polarys.appleitour.api.ApiComment;
import com.polarys.appleitour.helper.UIHelper;
import com.polarys.appleitour.model.Comment;
import com.polarys.appleitour.view.activity.HomeActivity;
import com.polarys.appleitour.view.activity.SeePostActivity;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {
    private final int id;
    private final String token;
    private final ArrayList<Comment> data;
    private final Context context;
    private ApiComment apiComment = new ApiComment();


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
        holder.email.setText(comment.getEmail());
        holder.text.setText(comment.getMessagePost());
        holder.date.setText(comment.getCreatedDate());
        holder.like.setVisibility(View.GONE);
        holder.comment.setVisibility(View.GONE);
        if(comment.getUserId() == id)
            holder.btnOptions.setVisibility(View.VISIBLE);
        holder.btnOptions.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(context, view);
            popupMenu.inflate(R.menu.menu_publication_options);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.publication_edit) {
                    UIHelper uiHelper = new UIHelper(context);
                    AlertDialog.Builder builder = uiHelper.createTextDialog(R.string.string_comment_edit_dialog_title,250);
                    builder.setPositiveButton("Enviar", (dialog, which) -> {
                        comment.setMessagePost(uiHelper.getText());
                        int success = apiComment.UpdateComment(comment, token).getCode();
                        int message = (success != (200 | 201)) ? R.string.string_comment_update_success : R.string.string_comment_update_error;
                        ((HomeActivity) context).showSnackBar(message);
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else if (itemId == R.id.publication_delete) {
                    UIHelper uiHelper = new UIHelper(context);
                    AlertDialog.Builder builder = uiHelper.createDialog(R.string.string_comment_dialog_title, R.string.string_comment_dialog_message, R.string.string_dialog_option_cancel);
                    builder.setPositiveButton("Confirmar", (dialog, which) -> {
                        int success = apiComment.DeleteComment(comment, token).getCode();
                        int message = (success != (200 | 201)) ? R.string.string_comment_delete_success : R.string.string_comment_delete_error;
                        ((SeePostActivity) context).showSnackBar(message);
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

    public class CommentHolder extends RecyclerView.ViewHolder {
        TextView user, text,email,date;
        ImageButton btnOptions, btnEdit;
        MaterialButton like,comment;
        LinearLayout mainLayout;

        public CommentHolder(@NonNull View view) {
            super(view);
            this.user = view.findViewById(R.id.txt_publication_username);
            this.email = view.findViewById(R.id.txt_publication_useremail);
            this.date = view.findViewById(R.id.txt_publication_date);
            this.text = view.findViewById(R.id.txt_publication_message);
            this.btnEdit = view.findViewById(R.id.publication_edit);
            this.btnOptions = view.findViewById(R.id.publication_options);
            this.like = view.findViewById(R.id.publication_btn_like);
            comment = view.findViewById(R.id.publication_comments_number);
            mainLayout = view.findViewById(R.id.item_publication_layout);
        }
    }
}