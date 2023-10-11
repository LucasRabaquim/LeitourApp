package com.polarys.appleitour.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.polarys.appleitour.R;
import com.polarys.appleitour.model.SavedBook;
import com.polarys.appleitour.view.adapter.PublicationAdapter;
import com.polarys.appleitour.view.adapter.SavedBookAdapter;
import com.polarys.appleitour.viewmodel.SavedBookViewModel;
import com.polarys.appleitour.viewmodel.SocialViewModel;

public class SavedBookActivity extends AppCompatActivity {

    private SavedBookViewModel viewModel;
    private RecyclerView recyclerView;
    private SavedBookAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_book);


    }
}