package com.polarys.appleitour.view;

import static com.polarys.appleitour.api.ApiUtil.JsonToArrayObject;
import static com.polarys.appleitour.api.ApiUtil.JsonToObject;
import static com.polarys.appleitour.helper.IntentHelper.BOOK_SHARED;
import static com.polarys.appleitour.helper.IntentHelper.SAVED_SHARED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.SavedBook;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.adapter.AnnotationAdapter;
import com.polarys.appleitour.viewmodel.BookInfoViewModel;
import com.squareup.picasso.Picasso;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.ViewHelper;
import com.polarys.appleitour.model.BookApi;

import java.util.ArrayList;

public class BookInfoActivity extends AppCompatActivity {

    private FloatingActionButton saveBook;
    private BookInfoViewModel viewModel;
    private BookApi book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        try {
            book = (BookApi) getIntent().getSerializableExtra(BOOK_SHARED);
        }catch (Exception e){
            book = null;
        }
        viewModel = ViewModelProviders.of(this).get(BookInfoViewModel.class);
        if(book != null)
            setViewData();
        saveBook = findViewById(R.id.btn_save_book);
        saveBook.setOnClickListener(v ->{
            SharedHelper sharedHelper = new SharedHelper(this);
            int userId = sharedHelper.GetUser().getId();
            String token = sharedHelper.GetToken();
            SavedBook savedBook = new SavedBook();
            ApiResponse apiResponse = savedBook.SetBook(book,userId,token);
            Toast.makeText(this, apiResponse.getBody(), Toast.LENGTH_SHORT).show();
        });

    }
    private void setViewData(){
        ImageView backgroundCover = findViewById(R.id.book_background);
        ImageView bookCover = findViewById(R.id.book_cover);
        ViewHelper viewHelper = new ViewHelper(this);
        viewHelper.setTextOfView(R.id.book_title,book.getTitle());
        viewHelper.setTextOfView(R.id.book_author,book.getAuthors());
        viewHelper.setTextOfView(R.id.book_publisher,book.getPublisher());
        viewHelper.setTextOfView(R.id.book_year,book.getPublishedDate());
        viewHelper.setTextOfView(R.id.txt_book_sinopse,book.getDescription());
        viewHelper.setTextOfView(R.id.book_language,book.getLanguage());
        viewHelper.setTextOfView(R.id.book_pages,String.valueOf(book.getPages()));
        viewHelper.setTextOfView(R.id.book_year,book.getPublishedDate());
        Picasso.get().load(book.getCover()).into(bookCover);
    }
}