package com.polarys.appleitour.view.adapter;

import static com.polarys.appleitour.helper.IntentHelper.ANNOTATION_SHARED;

import android.app.Activity;
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

import com.polarys.appleitour.R;
import com.polarys.appleitour.api.ApiAnnotation;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.helper.UIHelper;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.view.activity.AnnotationActivity;
import com.polarys.appleitour.view.activity.BookInfoActivity;
import com.polarys.appleitour.view.activity.SeePostActivity;

import java.util.ArrayList;

public class AnnotationAdapter extends RecyclerView.Adapter<AnnotationAdapter.AnnotationHolder>{
    private final ArrayList<Annotation> annotations;
    private Context context;
    private String token;
    private ApiAnnotation apiAnnotation = new ApiAnnotation();

    public AnnotationAdapter(ArrayList _annotations, Context _context,String _token) {
        annotations = _annotations;
        context = _context;
        token = _token;
    }

    @Override
    public AnnotationAdapter.AnnotationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AnnotationAdapter.AnnotationHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_annotation, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AnnotationAdapter.AnnotationHolder holder, int position) {

        Annotation annotation = annotations.get(position);
        holder.title.setText(annotation.getAnnotationText());
        holder.date.setText(annotation.getCreatedDate());
        holder.text.setText(annotation.getAnnotationText());
        holder.mainLayout.setOnLongClickListener(v -> {
            holder.text.setMaxLines(999);
            return true;
        });
        holder.btnOptions.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(context, view);
            popupMenu.inflate(R.menu.menu_publication_options);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.publication_edit) {
                    UIHelper uiHelper = new UIHelper(context);
                    AlertDialog.Builder builder = uiHelper.createTextDialog(R.string.string_comment_dialog_title);
                    builder.setPositiveButton("Enviar", (dialog, which) -> {
                        annotation.setAnnotationText(uiHelper.getText());
                        int success = apiAnnotation.UpdateAnnotation(annotation, token).getCode();
                        int message = (success != (200 | 201)) ? R.string.string_annotation_update_success : R.string.string_annotation_update_error;
                        ((BookInfoActivity) context).showSnackBar(message);
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else if (itemId == R.id.publication_delete) {
                    UIHelper uiHelper = new UIHelper(context);
                    AlertDialog.Builder builder = uiHelper.createDialog(R.string.string_annotation_dialog_title, R.string.string_annotation_dialog_message, R.string.string_dialog_option_cancel);
                    builder.setPositiveButton("Confirmar", (dialog, which) -> {
                        int success = apiAnnotation.DeleteAnnotation(annotation, token).getCode();
                        int message = (success != (200 | 201)) ? R.string.string_annotation_delete_success : R.string.string_annotation_delete_error;
                        ((BookInfoActivity) context).showSnackBar(message);
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
        return annotations != null ? annotations.size() : 0;
    }
    public class AnnotationHolder extends RecyclerView.ViewHolder{
        TextView title, date,text;
        ImageButton btnOptions;
        LinearLayout mainLayout;
        public AnnotationHolder(@NonNull View view){
            super(view);
            title= view.findViewById(R.id.annotation_title);
            date= view.findViewById(R.id.annotation_date);
            text = view.findViewById(R.id.annotation_text);
            btnOptions = view.findViewById(R.id.publication_options);
            mainLayout = view.findViewById(R.id.item_annotation_layout);
        }
    }
}
