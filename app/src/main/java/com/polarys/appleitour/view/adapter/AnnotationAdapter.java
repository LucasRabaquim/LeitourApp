package com.polarys.appleitour.view.adapter;

import static com.polarys.appleitour.helper.IntentHelper.ANNOTATION_SHARED;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.view.activity.AnnotationActivity;

import java.util.ArrayList;

public class AnnotationAdapter extends RecyclerView.Adapter<AnnotationAdapter.AnnotationHolder>{
    private final ArrayList<Annotation> annotations;
    private Context context;

    public AnnotationAdapter(ArrayList annotations, Context context) {
        this.annotations = annotations;
        this.context = context;
    }

    @Override
    public AnnotationAdapter.AnnotationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AnnotationAdapter.AnnotationHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AnnotationAdapter.AnnotationHolder holder, int position) {

        Annotation annotation = annotations.get(position);
       // Picasso.get().load(annotations.getCover()).into(holder.cover);
        holder.title.setText(annotation.getAnnotationText());
      //  holder.author.setText(annotations.getAuthors());
        holder.mainLayout.setOnClickListener(view -> {
            IntentHelper intentHelper = new IntentHelper((Activity) context, ANNOTATION_SHARED);
            intentHelper.nextActivityObj(AnnotationActivity.class,annotation);
        });

    }

    @Override
    public int getItemCount() {
        return annotations != null ? annotations.size() : 0;
    }
    public class AnnotationHolder extends RecyclerView.ViewHolder{
        TextView title, author;
        LinearLayout mainLayout;
        public AnnotationHolder(@NonNull View view){
            super(view);
            this.title= view.findViewById(R.id.item_annotation_book);
            this.author= view.findViewById(R.id.item_annotation_author);
            mainLayout = view.findViewById(R.id.item_annotation_layout);
        }
    }
}
