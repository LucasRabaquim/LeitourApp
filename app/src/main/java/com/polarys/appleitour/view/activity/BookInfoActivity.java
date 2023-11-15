package com.polarys.appleitour.view.activity;

import static android.content.Intent.getIntent;
import static com.polarys.appleitour.helper.IntentHelper.BOOK_SHARED;
import static com.polarys.appleitour.helper.IntentHelper.FROM_ACTIVITY_KEY;
import static com.polarys.appleitour.helper.IntentHelper.FROM_BOOKSEARCH;
import static com.polarys.appleitour.helper.IntentHelper.FROM_SAVEDBOOK;
import static com.polarys.appleitour.helper.IntentHelper.SAVED_SHARED;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.polarys.appleitour.R;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.helper.ViewHelper;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.BookApi;
import com.polarys.appleitour.model.SavedBook;
import com.polarys.appleitour.viewmodel.BookInfoViewModel;
import com.squareup.picasso.Picasso;

public class BookInfoActivity extends AppCompatActivity {

    private FloatingActionButton saveBook;
    private BookInfoViewModel viewModel;
    private BookApi book;
    private SavedBook savedBook;
    private ApiResponse response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        String fromActivity = getIntent().getStringExtra(FROM_ACTIVITY_KEY);
        SharedHelper sharedHelper = new SharedHelper(this);
        String token = sharedHelper.GetToken();
        Log.d("A",token);
        switch (fromActivity){
            case FROM_BOOKSEARCH:
                book = (BookApi) getIntent().getSerializableExtra(BOOK_SHARED);
                response = new SavedBook().GetSavedBook(book.getKey(), token);
                if (response.getCode() == 200)
                    savedBook = (SavedBook) ApiUtil.JsonToObject(new SavedBook(), response.getBody());
                else
                    savedBook = null;
                break;
                case FROM_SAVEDBOOK:
                savedBook = (SavedBook) getIntent().getSerializableExtra(SAVED_SHARED);
                response = new BookApi().GetByKey(savedBook.getBookKey());
                if (response.getCode() == 200)
                    book = (BookApi) ApiUtil.JsonToObject(new BookApi(), response.getBody());
                else
                    book = null;
                break;
        }

        viewModel = ViewModelProviders.of(this).get(BookInfoViewModel.class);
        if (book != null)
            setViewData();
        saveBook = findViewById(R.id.btn_save_book);
        saveBook.setOnClickListener(v -> {
            int userId = sharedHelper.GetUser().getId();
            SavedBook savedBook = new SavedBook();
            ApiResponse apiResponse = savedBook.SaveBook(book, userId, token);
            Toast.makeText(this, apiResponse.getBody(), Toast.LENGTH_SHORT).show();
        });

    }

    private void setViewData() {
        ImageView backgroundCover = findViewById(R.id.book_background);
        ImageView bookCover = findViewById(R.id.book_cover);
        ViewHelper.setTextOfView(this, R.id.book_title, book.getTitle());
        ViewHelper.setTextOfView(this, R.id.book_author, book.getAuthors());
        ViewHelper.setTextOfView(this, R.id.book_publisher, book.getPublisher());
        ViewHelper.setTextOfView(this, R.id.book_year, book.getPublishedDate());
        ViewHelper.setTextOfView(this, R.id.txt_book_sinopse, book.getDescription());
        ViewHelper.setTextOfView(this, R.id.book_language, book.getLanguage());
        ViewHelper.setTextOfView(this, R.id.book_pages, String.valueOf(book.getPages()));
        ViewHelper.setTextOfView(this, R.id.book_year, book.getPublishedDate());
        try {
            Picasso.get().load(book.getCover()).into(bookCover);
            Picasso.get().load(book.getCover()).into(backgroundCover);
        }catch(Exception e){}
    }
}