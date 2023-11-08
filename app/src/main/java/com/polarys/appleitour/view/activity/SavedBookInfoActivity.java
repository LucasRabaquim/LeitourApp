package com.polarys.appleitour.view.activity;

import static com.polarys.appleitour.helper.IntentHelper.SAVED_SHARED;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.SavedBook;
import com.polarys.appleitour.view.adapter.AnnotationAdapter;
import com.polarys.appleitour.viewmodel.SavedBookInfoViewModel;

import java.util.ArrayList;

public class SavedBookInfoActivity extends AppCompatActivity {

    private SavedBookInfoViewModel viewModel;
    private SavedBook book;
    private RecyclerView recyclerView;
    private AnnotationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_book_info);
        try {
            book = (SavedBook) getIntent().getSerializableExtra(SAVED_SHARED);
        } catch (Exception e) {
            book = null;
        }
        viewModel = new ViewModelProvider(this).get(SavedBookInfoViewModel.class);
        ArrayList<Annotation> annotations = viewModel.showAnnotations(book.getId(),new SharedHelper(this).GetToken());
        recyclerView = findViewById(R.id.recycler_annotation);
        adapter = new AnnotationAdapter(annotations, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        annotations.clear();
        annotations.addAll(annotations);
        adapter.notifyDataSetChanged();
    }
}